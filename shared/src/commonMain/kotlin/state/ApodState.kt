package state

import com.example.multimodule.coreNetwork.model.ApodResponse

sealed class ApodState {

    object Loading : ApodState()

    data class Success(
        val apod: ApodResponse?
    ) : ApodState()

    data class Error(
        val message: String
    ) : ApodState()
}