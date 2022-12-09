package com.los3molineros.lyophilization_world.ui.viewModels

import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.los3molineros.lyophilization_world.common.AppConstants.GOOGLE_TOKEN
import com.los3molineros.lyophilization_world.domain.SplashScreenUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
    var googleLogin: Boolean by mutableStateOf(false)
    var alreadyLoggedWithGoogle: Boolean by mutableStateOf(false)


    init {
        getDefaultTime()
        getFirebaseUser()
    }

    fun restartData() {
        _userAlreadyLoggedState.value = false
        googleLogin = false
        _errorState.value = null
        alreadyLoggedWithGoogle = false
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

    fun loginWithGoogle(context: Context, launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(GOOGLE_TOKEN)
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(context, gso)
        launcher.launch(googleSignInClient.signInIntent)
    }

    fun signWithGoogleCredentials(activityResult: ActivityResult) {
        viewModelScope.launch(Dispatchers.IO) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(activityResult.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
                try {
                    useCase.signGoogleWithCredential(credential, account)
                    _userAlreadyLoggedState.value = useCase.getCurrentUser()
                    alreadyLoggedWithGoogle = true
                } catch (e: Exception) {
                    _errorState.value = e.message
                }
            } catch (e: ApiException) {
                _errorState.value = e.message
            }
        }
    }

}