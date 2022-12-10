package com.los3molineros.lyophilization_world.ui.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.los3molineros.lyophilization_world.data.model.Post
import com.los3molineros.lyophilization_world.data.model.User
import com.los3molineros.lyophilization_world.domain.CommentUseCase
import com.los3molineros.lyophilization_world.ui.viewModels.interfaces.RefreshPostInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CommentViewModel(private val useCase: CommentUseCase): ViewModel() {
    // Firebase user
    private val _firebaseUserState = MutableStateFlow<User?>(null)
    val firebaseUserState: StateFlow<User?> get() = _firebaseUserState.asStateFlow()

    private val _postState = MutableStateFlow<Post?>(null)
    val postState: StateFlow<Post?> get() = _postState.asStateFlow()


    var comment: String by mutableStateOf("")
    private val refreshPostInterface: RefreshPostInterface? = null

    init {
        getUser()
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

    fun setComment() {
        if (comment.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                setPost(useCase.setComment(title = _postState.value?.title, comment = comment))
            }
        }
    }

    fun setPost(post: Post?) {
        Log.d("TAG", "setPost: en setPost con $post")
        _postState.value = post
        refreshPostInterface?.refreshPost(post)
    }
}