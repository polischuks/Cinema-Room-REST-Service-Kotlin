package cinema

import java.util.*

class Ticket(token: UUID, ticket: Seats?) {

    private val token: UUID
    private lateinit var ticket: Seats

    init {
        this.token = token
        if (ticket != null) {
            this.ticket = ticket
        }
    }
    fun getToken(): UUID {
        return token
    }

    fun getTicket(): Seats {
        return ticket
    }

    fun setTicket(ticket: Seats) {
        this.ticket = ticket
    }
}