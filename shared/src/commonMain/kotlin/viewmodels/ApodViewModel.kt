package viewmodels

import com.example.multimodule.coreNetwork.retrofit.NasaApi
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import state.Resource

class ApodViewModel(
    private val nasaApi: NasaApi
) : ViewModel() {

    private val _apodState = MutableStateFlow<Resource>(viewModelScope, Resource.Loading)
    val apodState: StateFlow<Resource> = _apodState.asStateFlow()

    init {
        println("✅ ApodViewModel created")
    }

    fun loadTodayApod() {
        println("📡 loadTodayApod called")

        viewModelScope.launch {

            _apodState.value = Resource.Loading

            try {

                val response = nasaApi.getApod(
                    null,
                    null,
                    null,
                    null,
                    false,
                    "sNOZ0Gh92DCCf0Tol6GWOwU5akR0CXmtJPOvAkmV"
                )

                println("📡 API response = $response")

                _apodState.value =
                    Resource.Success(response.firstOrNull())

            } catch (e: Exception) {

                _apodState.value =
                    Resource.Error(e.message ?: "Unknown error")

            }
        }
    }
}