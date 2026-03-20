package state

import com.example.multimodule.coreNetwork.model.ApodResponse

sealed class Resource {

    object Loading : Resource()

    data class Success(
        val data: ApodResponse?
    ) : Resource()

    data class Error(
        val message: String
    ) : Resource()
}