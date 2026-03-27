enum class AppEnvironment {
    DEV,
    STAGING,
    PROD
}

object Environment {

    var current: AppEnvironment = AppEnvironment.DEV

    val baseUrl: String
        get() = when (current) {
            AppEnvironment.DEV -> "https://api.nasa.gov"
            AppEnvironment.STAGING -> "https://api.nasa.gov"
            AppEnvironment.PROD -> "https://api.nasa.gov"
        }
}