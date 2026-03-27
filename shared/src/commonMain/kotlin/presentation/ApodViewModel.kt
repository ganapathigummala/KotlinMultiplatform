package presentation

import com.example.multimodule.coreNetwork.model.ApodResponse
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

    private val _apodState = MutableStateFlow<Resource<ApodResponse?>>(viewModelScope, Resource.Loading)

    val apodState: StateFlow<Resource<ApodResponse?>> = _apodState.asStateFlow()

    init {
        println("✅ ApodViewModel created")
    }

    fun loadTodayApod() {

        viewModelScope.launch {

            _apodState.value = Resource.Loading

            when (
                val result = nasaApi.getApod(
                    null,
                    null,
                    null,
                    null,
                    false,
                    "sNOZ0Gh92DCCf0Tol6GWOwU5akR0CXmtJPOvAkmV"
                )
            ) {

                is Resource.Success -> {

                    _apodState.value =
                        Resource.Success(result.data.firstOrNull())
                }

                is Resource.Error -> {

                    _apodState.value =
                        Resource.Error(result.error)
                }

                Resource.Loading -> {}
            }
        }
    }
}