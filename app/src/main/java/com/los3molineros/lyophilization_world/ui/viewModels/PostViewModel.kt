package com.los3molineros.lyophilization_world.ui.viewModels

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.los3molineros.lyophilization_world.BuildConfig
import com.los3molineros.lyophilization_world.common.AppConstants
import com.los3molineros.lyophilization_world.data.model.Post
import com.los3molineros.lyophilization_world.data.model.User
import com.los3molineros.lyophilization_world.domain.PostUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PostViewModel(private val useCase: PostUseCase): ViewModel() {
    // Firebase user
    private val _firebaseUserState = MutableStateFlow<User?>(null)
    val firebaseUserState: StateFlow<User?> get() = _firebaseUserState.asStateFlow()

    private val _postListState = MutableStateFlow<List<Post>>(listOf())
    val postListState: StateFlow<List<Post>> get() = _postListState.asStateFlow()


    init {
        getUser()
        getPosts()
    }

    private fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _firebaseUserState.value = useCase.getUser()
            } catch (e: Exception) {
                _firebaseUserState.value = null
            }
        }
    }


    private fun getPosts() {
        viewModelScope.launch(Dispatchers.IO)  {
            try {
                _postListState.value = useCase.getPosts()
            } catch (e: Exception) {
                _postListState.value = listOf()
            }
        }
    }

    fun favouriteClicked(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _firebaseUserState.value?.uid?.let {
                useCase.favouriteClicked(title, it, _postListState.value)
            }
        }
    }

    fun imageClicked(context: Context, link: String?) {
        link?.let {
            val goToLink = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(link)
            )
            context.startActivity(goToLink)
        }
    }

    fun contactUs(context: Context) {
        val goToLink = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(AppConstants.CONTACT_US)
        )
        context.startActivity(goToLink)
    }


    fun rateUs(context: Context) {
        val goToMarket = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("${AppConstants.APP_STORE}${BuildConfig.APPLICATION_ID}")
        )
        context.startActivity(goToMarket)
    }

    fun signOut(context: Context) {
        useCase.logOut()

        GoogleSignIn
            .getClient(
                context,
                GoogleSignInOptions
                    .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .build()
            ).signOut()
    }

}