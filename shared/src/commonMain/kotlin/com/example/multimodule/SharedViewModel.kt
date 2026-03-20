package com.example.multimodule

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedViewModel : ViewModel() {
    private val  uiState = MutableStateFlow(viewModelScope,"")
    @NativeCoroutinesState
    val uiData: StateFlow<String> = uiState.asStateFlow()

    init {
        getData()
    }

    fun getData(){
        viewModelScope.launch {
            repeat(20){
                delay(1000)
                uiState.value = it.toString()
            }
        }
    }
}