package cinema

class LoginException(private var error: Error?): RuntimeException()  {

    fun getError(): Error? {
        return error
    }
}