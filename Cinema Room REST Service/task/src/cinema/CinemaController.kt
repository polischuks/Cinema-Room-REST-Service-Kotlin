package cinema

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.AbstractMap.SimpleEntry
import java.util.concurrent.ConcurrentHashMap

@RestController
class CinemaController {

    private var row = 9
    private var column = 9
    private var cinemaRoom = CinemaRoom(row, column)
    private var tickets = ConcurrentHashMap<UUID, Seats>()

    @GetMapping("/seats")
    fun getInfoCinema(): CinemaRoom {
        return cinemaRoom
    }

    @PostMapping("/purchase")
    fun purchase(@RequestBody seat: Seats): Ticket {
        val row = seat.getRow()
        val column = seat.getColumn()
        if (isOutOfBounds(row, this.row) || isOutOfBounds(column, this.column)) {
            throw PurchaseException(Error("The number of a row or a column is out of bounds!"))
        }
        val availableSeat: Seats? = cinemaRoom.getSeatWithSameRowColumn(seat)
        if (availableSeat != null) {
            val token = UUID.randomUUID()
            tickets[token] = availableSeat
            cinemaRoom.deleteAvailableSeat(availableSeat)
            return Ticket(token, availableSeat)
        }
        throw PurchaseException(Error("The ticket has been already purchased!"))
    }

    @PostMapping("/return")
    fun returnTicket(@RequestBody token: Ticket): SimpleEntry<String, Seats?> {
        val tokenToReturn = token.getToken()
        if (tickets.containsKey(tokenToReturn)) {
            val returnedTicket: Seats? = tickets[tokenToReturn]
            if (returnedTicket != null) {
                cinemaRoom.addAvailableSeat(returnedTicket)
            }
            tickets.remove(tokenToReturn)
            return SimpleEntry("returned_ticket", returnedTicket)
        }
        throw PurchaseException(Error("Wrong token!"))
    }

    @PostMapping("/stats")
    fun getStats(@RequestParam(value = "password", required = false) password: String?): Statistics {
        if (password == null) throw LoginException(Error("The password is wrong!"))
        if (password == "super_secret") {
            return Statistics(getCurrentIncome(), cinemaRoom.getAvailableSeats().size, tickets.size)
        }
        throw LoginException(Error("The password is wrong!"))
    }

    private fun getCurrentIncome(): Int {
        var currentIncome = 0
        for (seat in tickets.values) {
            currentIncome += seat.getPrice()
        }
        return currentIncome
    }

    private fun isOutOfBounds(number: Int, max: Int): Boolean {
        return number < 1 || number > max
    }

    @ExceptionHandler(PurchaseException::class)
    fun handleTheatreException(exception: PurchaseException): ResponseEntity<Error?>? {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(exception.getError())
    }

    @ExceptionHandler(LoginException::class)
    fun handleTheatreException(exception: LoginException): ResponseEntity<Error?>? {
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(exception.getError())
    }
}