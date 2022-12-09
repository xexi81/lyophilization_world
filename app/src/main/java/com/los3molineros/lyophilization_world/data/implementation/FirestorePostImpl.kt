package com.los3molineros.lyophilization_world.data.implementation

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.los3molineros.lyophilization_world.data.model.Post
import com.los3molineros.lyophilization_world.data.repositories.FirestorePostsRepository
import kotlinx.coroutines.tasks.await

class FirestorePostImpl(private val firebaseFirestore: FirebaseFirestore): FirestorePostsRepository {
    override suspend fun getPosts(): List<Post> {
        val recipesList: MutableList<Post> = mutableListOf()
        val result = firebaseFirestore.collection("posts").orderBy("dateCreation", Query.Direction.DESCENDING).get().await()

        for (item in result) {
            recipesList.add(item.toObject(Post::class.java))
        }

        return recipesList
    }

}