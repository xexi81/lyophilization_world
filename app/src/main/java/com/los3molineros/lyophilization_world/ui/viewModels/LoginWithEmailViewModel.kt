package com.los3molineros.lyophilization_world.ui.viewModels

import android.accounts.NetworkErrorException
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.los3molineros.lyophilization_world.common.enums.FirebaseLoginErrorEnum
import com.los3molineros.lyophilization_world.common.isEmailValid
import com.los3molineros.lyophilization_world.domain.FirebaseLoginUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginWithEmailViewModel(private val useCase: FirebaseLoginUseCase): ViewModel() {
    var email : String by mutableStateOf("")
    var password: String by mutableStateOf("")
    var error: FirebaseLoginErrorEnum? by mutableStateOf(null)
    var externalError: String? by mutableStateOf(null)
    var navigateToPost: Boolean by mutableStateOf(false)

    fun restartError() {
        error = null
        externalError = null
    }

    fun setNavigateToFalse() { navigateToPost = false }

    private fun validateEmailAndPassword() {
        restartError()
        validateEmail()
        validatePassword()
    }

    private fun validateEmail() {
        if (email.isEmpty()) { error = FirebaseLoginErrorEnum.EMPTY_FIELDS_ERROR }

        if (!email.isEmailValid()) { error = FirebaseLoginErrorEnum.MAIL_ERROR }
    }

    private fun validatePassword() {
        if (password.isEmpty()) { error = FirebaseLoginErrorEnum.EMPTY_FIELDS_ERROR }
    }

    fun setEmailField(email: String) { this.email = email }

    fun setPasswordField(password: String) { this.password = password }

    fun signIn(){
        validateEmailAndPassword()
        if (error == null) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    useCase.loginUser(email, password)
                    navigateToPost = true
                } catch (e: NetworkErrorException) {
                    error = FirebaseLoginErrorEnum.CONNECTION_ERROR
                } catch (e: Exception) {
                    externalError = e.message
                }
            }
        }
    }

    fun signUp() {
        validateEmailAndPassword()
        if (error == null) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    useCase.createUser(email, password)
                    navigateToPost = true
                } catch (e: NetworkErrorException) {
                    error = FirebaseLoginErrorEnum.CONNECTION_ERROR
                } catch (e: Exception) {
                    externalError = e.message
                }
            }
        }
    }

    fun forgetPassword() {
        restartError()
        validateEmail()
        if (error == null) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    useCase.restartUser(email)
                    error = FirebaseLoginErrorEnum.RESTART_OK
                } catch (e: NetworkErrorException) {
                    error = FirebaseLoginErrorEnum.CONNECTION_ERROR
                } catch (e: Exception) {
                    externalError = e.message
                }
            }
        }
    }
}