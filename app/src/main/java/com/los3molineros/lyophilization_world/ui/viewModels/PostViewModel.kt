package com.los3molineros.lyophilization_world.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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



}