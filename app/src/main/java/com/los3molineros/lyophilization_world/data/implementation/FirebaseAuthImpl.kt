package com.los3molineros.lyophilization_world.data.implementation

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.los3molineros.lyophilization_world.data.repositories.FirebaseAuthRepository

class FirebaseAuthImpl(private val firebaseAuth: FirebaseAuth): FirebaseAuthRepository {
    override suspend fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }
}