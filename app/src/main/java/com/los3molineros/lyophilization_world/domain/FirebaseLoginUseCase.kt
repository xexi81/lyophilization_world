package com.los3molineros.lyophilization_world.domain

import android.accounts.NetworkErrorException
import com.los3molineros.lyophilization_world.common.isNetworkAvailable
import com.los3molineros.lyophilization_world.data.repositories.FirebaseAuthRepository
import com.los3molineros.lyophilization_world.data.repositories.FirestoreUserRepository

class FirebaseLoginUseCase(private val firebaseRepository: FirebaseAuthRepository, private val firestoreUserRepository: FirestoreUserRepository) {
    suspend fun createUser(email: String,  password: String): Boolean {
        if (isNetworkAvailable())
        {
            val user = firebaseRepository.createUser(email, password)
            firestoreUserRepository.createUser(
                uid = user?.uid,
                displayName = user?.displayName,
                providerId = "Firebase",
                email = user?.email,
                photoUrl = user?.photoUrl.toString())

            return user == null
        } else { throw NetworkErrorException("No connection available") }
    }

    suspend fun loginUser(email: String, password: String): Boolean {
        if (isNetworkAvailable())
        { return firebaseRepository.loginUser(email, password) == null
        } else { throw NetworkErrorException("No connection available") }
    }

    suspend fun restartUser(email: String) {
        if (isNetworkAvailable())
        { firebaseRepository.rememberPassword(email)
        } else { throw NetworkErrorException("No connection available") }
    }
}