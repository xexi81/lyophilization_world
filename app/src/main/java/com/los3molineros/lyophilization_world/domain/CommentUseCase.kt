package com.los3molineros.lyophilization_world.domain

import android.accounts.NetworkErrorException
import com.los3molineros.lyophilization_world.common.isNetworkAvailable
import com.los3molineros.lyophilization_world.data.model.Post
import com.los3molineros.lyophilization_world.data.model.User
import com.los3molineros.lyophilization_world.data.repositories.FirebaseAuthRepository
import com.los3molineros.lyophilization_world.data.repositories.FirestorePostsRepository
import com.los3molineros.lyophilization_world.data.repositories.FirestoreUserRepository

class CommentUseCase(
    private val firebaseAuthRepository: FirebaseAuthRepository,
    private val firestoreUserRepository: FirestoreUserRepository,
    private val firestorePostsRepository: FirestorePostsRepository
) {
    suspend fun getUser(): User? {
        if (isNetworkAvailable()) {
            return firestoreUserRepository.getUser(firebaseAuthRepository.getCurrentUser()?.uid!!)
        } else {
            throw NetworkErrorException("No connection available")
        }
    }

    suspend fun setComment(title: String?, comment: String): Post {
        if (!isNetworkAvailable()) { throw NetworkErrorException("No connection available") }
        if (title == null) { throw NullPointerException("Empty Post in CommentScreen")}

        val user = firestoreUserRepository.getUser(firebaseAuthRepository.getCurrentUser()?.uid!!)
        user?.let {
            return firestorePostsRepository.setComment(user = user, title = title, comment = comment)
        }

        throw NullPointerException("Empty Post in comment Response")
    }
}