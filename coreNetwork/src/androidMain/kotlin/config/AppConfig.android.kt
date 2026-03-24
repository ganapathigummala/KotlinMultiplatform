package config

import com.example.multimodule.coreNetwork.BuildConfig

actual object AppConfig {
    actual val isDebug: Boolean = BuildConfig.DEBUG
}