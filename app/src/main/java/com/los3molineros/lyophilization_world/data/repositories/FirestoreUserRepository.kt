package com.los3molineros.lyophilization_world.data.repositories

interface FirestoreUserRepository {
    suspend fun createUser(uid: String?, displayName: String?, providerId: String?, email: String?, photoUrl: String?)
}