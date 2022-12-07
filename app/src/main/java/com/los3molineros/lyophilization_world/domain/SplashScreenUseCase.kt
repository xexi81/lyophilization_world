package com.los3molineros.lyophilization_world.domain

import android.accounts.NetworkErrorException
import com.los3molineros.lyophilization_world.common.isNetworkAvailable
import com.los3molineros.lyophilization_world.data.repositories.FirebaseAuthRepository

class SplashScreenUseCase(private val firebaseAuthRepository: FirebaseAuthRepository) {
    suspend fun getCurrentUser(): Boolean {
        if (isNetworkAvailable()) {
            if (firebaseAuthRepository.getCurrentUser() != null) {
                return true
            }
        } else {
            throw NetworkErrorException("No connection available")
        }
        return false
    }
}