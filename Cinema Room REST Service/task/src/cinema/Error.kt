package cinema

class Error(private var error: String) {

    fun getError(): String {
        return error
    }

    fun setError(error: String) {
        this.error = error
    }
}