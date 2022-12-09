package com.los3molineros.lyophilization_world.data.implementation

import com.google.firebase.firestore.FirebaseFirestore
import com.los3molineros.lyophilization_world.data.model.User
import com.los3molineros.lyophilization_world.data.repositories.FirestoreUserRepository
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.*

class FirestoreUserImpl(private val firebaseFirestore: FirebaseFirestore): FirestoreUserRepository {

    override suspend fun createUser(
        uid: String?,
        displayName: String?,
        providerId: String?,
        email: String?,
        photoUrl: String?
    ) {

        uid?.let {
            val document = firebaseFirestore.collection("users").document(uid).get().await()

            if (document.data.isNullOrEmpty()) {
                val user = User(uid, displayName, email, providerId, photoUrl)
                firebaseFirestore.collection("users").document(uid).set(user)
            }
        }
    }


    override suspend fun getUser(uid: String): User? {
        return firebaseFirestore.collection("users").document(uid).get().await().toObject(User::class.java)
    }
}