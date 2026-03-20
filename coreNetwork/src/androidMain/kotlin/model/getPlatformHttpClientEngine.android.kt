package com.example.multimodule.coreNetwork.expectFunction

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

actual fun getPlatformHttpClientEngine(): HttpClientEngine {
    return OkHttp.create()
}