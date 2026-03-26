package network

sealed class NetworkError {

    object NoInternet : NetworkError()

    object Timeout : NetworkError()

    object Unauthorized : NetworkError()

    object Forbidden : NetworkError()

    object NotFound : NetworkError()

    object TooManyRequests : NetworkError()

    object ServerError : NetworkError()

    object Serialization : NetworkError()

    data class ApiError(
        val code: Int,
        val message: String?
    ) : NetworkError()

    object Unknown : NetworkError()
}