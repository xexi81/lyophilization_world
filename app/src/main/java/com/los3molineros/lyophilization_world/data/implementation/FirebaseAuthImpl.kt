package com.los3molineros.lyophilization_world.data.implementation

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.los3molineros.lyophilization_world.data.repositories.FirebaseAuthRepository
import kotlinx.coroutines.tasks.await

class FirebaseAuthImpl(private val firebaseAuth: FirebaseAuth): FirebaseAuthRepository {
    override suspend fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    override suspend fun createUser(email: String, password: String): FirebaseUser? {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        return result.user
    }

    override suspend fun loginUser(email: String, password: String): FirebaseUser? {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        return result.user
    }

    override suspend fun rememberPassword(email: String) {
        firebaseAuth.sendPasswordResetEmail(email).await()
    }

    override suspend fun signInWithCredentials(credential: AuthCredential) {
        firebaseAuth.signInWithCredential(credential).await()
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }
}