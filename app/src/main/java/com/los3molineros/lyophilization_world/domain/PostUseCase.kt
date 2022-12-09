package com.los3molineros.lyophilization_world.domain

import android.accounts.NetworkErrorException
import com.los3molineros.lyophilization_world.common.isNetworkAvailable
import com.los3molineros.lyophilization_world.data.model.Post
import com.los3molineros.lyophilization_world.data.model.User
import com.los3molineros.lyophilization_world.data.repositories.FirebaseAuthRepository
import com.los3molineros.lyophilization_world.data.repositories.FirestorePostsRepository
import com.los3molineros.lyophilization_world.data.repositories.FirestoreUserRepository

class PostUseCase(
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

    suspend fun getPosts(): List<Post> {
        if (isNetworkAvailable()) {
            return firestorePostsRepository.getPosts()
        } else {
            throw NetworkErrorException("No connection available")
        }
    }
}