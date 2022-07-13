package cinema

import com.fasterxml.jackson.annotation.JsonProperty

class CinemaRoom(row: Int, column: Int) {

    @JsonProperty("total_rows")
    private var totalRows = row

    @JsonProperty("total_columns")
    private var totalColumns = column

    @JsonProperty("available_seats")
    private var availableSeats: MutableList<Seats> = ArrayList()


    init {

        for (i in 1 .. totalRows) {
            for (j in 1 .. totalColumns) {
                if (i <= 4) {
                    availableSeats.add(Seats(i, j,  10,false))
                } else availableSeats.add(Seats(i, j,  8,false))
            }
        }
    }

    fun getSeatWithSameRowColumn(seat: Seats): Seats? {
        for (seats in availableSeats) {
            if (seats.getRow() == seat.getRow() &&
                seats.getColumn() == seat.getColumn()
            ) {
                return seats
            }
        }
        return null
    }

    fun deleteAvailableSeat(seat: Seats) {
        availableSeats.remove(seat)
    }

    fun addAvailableSeat(seat: Seats) {
        availableSeats.add(seat)
    }

    fun getAvailableSeats(): List<Seats> {
        return availableSeats
    }
}