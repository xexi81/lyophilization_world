package com.los3molineros.lyophilization_world.data.repositories

import com.google.firebase.auth.FirebaseUser

interface FirebaseAuthRepository {
    suspend fun getCurrentUser(): FirebaseUser?
}