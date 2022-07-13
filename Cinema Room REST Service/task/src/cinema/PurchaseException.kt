package cinema

class PurchaseException(private val error: Error): RuntimeException() {

    fun getError(): Error {
        return error
    }
}