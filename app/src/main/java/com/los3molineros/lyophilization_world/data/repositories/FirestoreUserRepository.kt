package com.los3molineros.lyophilization_world.data.repositories

import com.los3molineros.lyophilization_world.data.model.User

interface FirestoreUserRepository {
    suspend fun createUser(uid: String?, displayName: String?, providerId: String?, email: String?, photoUrl: String?)
    suspend fun getUser(uid: String): User?
}