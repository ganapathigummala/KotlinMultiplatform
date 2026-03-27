package com.example.multimodule.coreNetwork.retrofit

import com.example.multimodule.coreNetwork.model.ApodResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.*
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.isSuccess
import kotlinx.io.IOException
import logger.Logger
import network.NetworkError
import state.Resource
import kotlinx.serialization.SerializationException

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
    ): Resource<List<ApodResponse>> {

        return try {

            val response = client.get("${Environment.baseUrl}/planetary/apod") {

                parameter("api_key", apiKey)

                date?.let { parameter("date", it) }
                startDate?.let { parameter("start_date", it) }
                endDate?.let { parameter("end_date", it) }
                count?.let { parameter("count", it) }

                parameter("thumbs", thumbs)
            }

            if (!response.status.isSuccess()) {

                Logger.d("gana", "API Error: ${response.status}")

                return when (response.status.value) {

                    401 -> Resource.Error(NetworkError.Unauthorized)

                    403 -> Resource.Error(NetworkError.Forbidden)

                    404 -> Resource.Error(NetworkError.NotFound)

                    429 -> Resource.Error(NetworkError.TooManyRequests)

                    in 500..599 -> Resource.Error(NetworkError.ServerError)

                    else -> Resource.Error(
                        NetworkError.ApiError(
                            response.status.value,
                            "Unknown API error"
                        )
                    )
                }
            }

            val list: List<ApodResponse> = try {

                response.body()

            } catch (e: Exception) {

                val single: ApodResponse = response.body()

                listOf(single)
            }

            Resource.Success(list)

        } catch (e: HttpRequestTimeoutException) {

            Resource.Error(NetworkError.Timeout)

        } catch (e: IOException) {

            Resource.Error(NetworkError.NoInternet)

        } catch (e: SerializationException) {

            Resource.Error(NetworkError.Serialization)

        } catch (e: Exception) {

            Logger.d("gana", "Unknown Error: ${e.message}")

            Resource.Error(NetworkError.Unknown)
        }
    }
}