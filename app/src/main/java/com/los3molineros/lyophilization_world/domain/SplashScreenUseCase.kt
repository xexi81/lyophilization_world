package com.los3molineros.lyophilization_world.domain

import android.accounts.NetworkErrorException
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.AuthCredential
import com.los3molineros.lyophilization_world.common.isNetworkAvailable
import com.los3molineros.lyophilization_world.data.repositories.FirebaseAuthRepository
import com.los3molineros.lyophilization_world.data.repositories.FirestoreUserRepository

class SplashScreenUseCase(
    private val firebaseAuthRepository: FirebaseAuthRepository,
    private val firestoreUserRepository: FirestoreUserRepository
    )
{
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

    suspend fun signGoogleWithCredential(credential: AuthCredential, account: GoogleSignInAccount) {
        if (isNetworkAvailable()) {
            firebaseAuthRepository.signInWithCredentials(credential)
            val userUid = firebaseAuthRepository.getCurrentUser()?.uid

            if (userUid == null) {
                throw NullPointerException("something wrong with Google login")
            } else {
                firestoreUserRepository.createUser(
                    uid = userUid,
                    displayName = account.displayName,
                    providerId = "Google",
                    email = account.email,
                    photoUrl = account.photoUrl.toString())
            }
        } else {
            throw NetworkErrorException("No connection available")
        }
    }
}