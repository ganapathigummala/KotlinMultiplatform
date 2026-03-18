package com.example.multimodule.core.network.di

import com.example.multimodule.coreNetwork.model.NasaApi
import com.example.multimodule.coreNetwork.model.getPlatformHttpClientEngine
import io.ktor.client.HttpClient
import org.koin.dsl.module

import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

import io.ktor.client.plugins.logging.*
import kotlinx.coroutines.NonCancellable.get

val NetworkModule = module {

    single {
        HttpClient(getPlatformHttpClientEngine()) {

            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        isLenient = true
                        prettyPrint = true
                    }
                )
            }

            install(Logging) {
                level = LogLevel.INFO
            }
        }
    }

    single { NasaApi(get()) }
}