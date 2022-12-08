package com.los3molineros.lyophilization_world.data.implementation

import com.google.firebase.firestore.FirebaseFirestore
import com.los3molineros.lyophilization_world.data.model.User
import com.los3molineros.lyophilization_world.data.repositories.FirestoreUserRepository
import java.text.SimpleDateFormat
import java.util.*

class FirestoreUserImpl(private val firebaseFirestore: FirebaseFirestore): FirestoreUserRepository {
    private val sdf = SimpleDateFormat("EEE, d MMM yyyy")

    override suspend fun createUser(
        uid: String?,
        displayName: String?,
        providerId: String?,
        email: String?,
        photoUrl: String?
    ) {
        val currentDate: String = sdf.format(Date())

        uid?.let {
            val user = User(uid, displayName, email, providerId, photoUrl)
            firebaseFirestore.collection("users").document(uid).set(user)
        }
    }
}