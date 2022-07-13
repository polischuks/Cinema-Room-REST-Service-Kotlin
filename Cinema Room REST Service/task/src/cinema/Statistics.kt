package cinema

import com.fasterxml.jackson.annotation.JsonProperty

class Statistics(

    @JsonProperty("current_income")
    private var currentIncome: Int,

    @JsonProperty("number_of_available_seats")
    private var numberOfAvailableSeats: Int,

    @JsonProperty("number_of_purchased_tickets")
    private var numberOfPurchasedTickets: Int
) {

    fun getCurrent_income(): Int {
        return currentIncome
    }

    fun setCurrent_income(current_income: Int) {
        this.currentIncome = current_income
    }

    fun getNumber_of_available_seats(): Int {
        return numberOfAvailableSeats
    }

    fun setNumber_of_available_seats(number_of_available_seats: Int) {
        this.numberOfAvailableSeats = number_of_available_seats
    }

    fun getNumber_of_purchased_tickets(): Int {
        return numberOfPurchasedTickets
    }

    fun setNumber_of_purchased_tickets(number_of_purchased_tickets: Int) {
        this.numberOfPurchasedTickets = number_of_purchased_tickets
    }
}