package com.los3molineros.lyophilization_world.domain

import android.accounts.NetworkErrorException
import com.los3molineros.lyophilization_world.common.isNetworkAvailable
import com.los3molineros.lyophilization_world.data.repositories.FirebaseAuthRepository

class FirebaseLoginUseCase(private val firebaseRepository: FirebaseAuthRepository) {
    suspend fun createUser(email: String,  password: String): Boolean {
        if (isNetworkAvailable())
        { return firebaseRepository.createUser(email, password) == null
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