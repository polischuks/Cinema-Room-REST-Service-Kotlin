package cinema

class Seats{

    private var row = 0
    private var column = 0
    private var price = 0
    private var status: Boolean? = null

    constructor (row: Int, column: Int, price: Int, status: Boolean?) {
        this.row = row
        this.column = column
        this.price = price
        this.status = status
    }

    fun getRow(): Int {
        return row
    }

    fun setRow(row: Int) {
        this.row = row
    }

    fun getColumn(): Int {
        return column
    }

    fun setColumn(column: Int) {
        this.column = column
    }
    fun getPrice(): Int {
        return price
    }

    fun setPrice(price: Int) {
        this.price = price
    }
}