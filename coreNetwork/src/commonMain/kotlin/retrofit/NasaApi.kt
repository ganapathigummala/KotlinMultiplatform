package com.example.multimodule.coreNetwork.retrofit

import com.example.multimodule.coreNetwork.model.ApodResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.isSuccess
import logger.Logger

class NasaApi(
    private val client: HttpClient,
) {

    suspend fun getApod(
        date: String?,
        startDate: String?,
        endDate: String?,
        count: Int?,
        thumbs: Boolean,
        apiKey: String
    ): List<ApodResponse> {


        val response = client.get("${Environment.baseUrl}/planetary/apod") {
            parameter("api_key", apiKey)
            date?.let { parameter("date", it) }
            startDate?.let { parameter("start_date", it) }
            endDate?.let { parameter("end_date", it) }
            count?.let { parameter("count", it) }
            parameter("thumbs", thumbs)
        }
        Logger.d("gana","${response.status}")

        if (!response.status.isSuccess()) {
            println("❌ API error: ${response.status}")
            Logger.d("gana","${response.status}")
            return emptyList()
        }

        return try {

            val list: List<ApodResponse> = response.body()

            println("✅ Received list size: ${list.size}")

            list

        } catch (e: Exception) {

            val single: ApodResponse = response.body()

            println("✅ Received single APOD: ${single.title}")

            listOf(single)
        }
    }
}