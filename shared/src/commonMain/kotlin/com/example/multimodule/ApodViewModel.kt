package com.example.multimodule

import com.example.multimodule.coreNetwork.model.NasaApi
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import state.ApodState

class ApodViewModel(
    private val nasaApi: NasaApi
) : ViewModel() {

    private val _apodState = MutableStateFlow<ApodState>(viewModelScope,ApodState.Loading)
    val apodState: StateFlow<ApodState> = _apodState.asStateFlow()

    init {
        println("✅ ApodViewModel created")
    }

    fun loadTodayApod() {
        println("📡 loadTodayApod called")

        viewModelScope.launch {

            _apodState.value = ApodState.Loading

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
                    ApodState.Success(response.firstOrNull())

            } catch (e: Exception) {

                _apodState.value =
                    ApodState.Error(e.message ?: "Unknown error")

            }
        }
    }
}