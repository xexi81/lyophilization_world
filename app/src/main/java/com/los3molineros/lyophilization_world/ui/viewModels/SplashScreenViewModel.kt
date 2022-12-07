package com.los3molineros.lyophilization_world.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.los3molineros.lyophilization_world.domain.SplashScreenUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

class SplashScreenViewModel(
    private val useCase: SplashScreenUseCase
): ViewModel() {

    // FirebaseUser
    private val _userAlreadyLoggedState = MutableStateFlow<Boolean?>(null)
    val userAlreadyLoggedState: StateFlow<Boolean?> get() = _userAlreadyLoggedState.asStateFlow()

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> get() = _errorState.asStateFlow()

    var timeHasGone: Boolean? by mutableStateOf(null)


    init {
        getDefaultTime()
        getFirebaseUser()
    }


    private fun getDefaultTime() {
        viewModelScope.launch(Dispatchers.IO) {
            sleep(3000)
            timeHasGone = true
        }
    }

    private fun getFirebaseUser() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _userAlreadyLoggedState.value = useCase.getCurrentUser()
            } catch (e: Exception) {
                _errorState.value = e.message
            }
        }
    }
}