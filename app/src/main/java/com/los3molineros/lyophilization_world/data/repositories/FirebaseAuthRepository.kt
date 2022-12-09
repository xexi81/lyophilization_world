package com.los3molineros.lyophilization_world.data.repositories

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser

interface FirebaseAuthRepository {
    suspend fun getCurrentUser(): FirebaseUser?
    suspend fun createUser(email: String, password: String): FirebaseUser?
    suspend fun loginUser(email:String, password: String): FirebaseUser?
    suspend fun rememberPassword(email: String)
    suspend fun signInWithCredentials(credential: AuthCredential)
    fun signOut()
}