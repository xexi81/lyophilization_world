package com.los3molineros.lyophilization_world.data.implementation

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.los3molineros.lyophilization_world.data.model.Favourite
import com.los3molineros.lyophilization_world.data.model.Post
import com.los3molineros.lyophilization_world.data.model.PostFavourite
import com.los3molineros.lyophilization_world.data.repositories.FirestorePostsRepository
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.*

class FirestorePostImpl(private val firebaseFirestore: FirebaseFirestore): FirestorePostsRepository {
    private val sdf = SimpleDateFormat("EEE, d MMM yyyy")

    override suspend fun getPosts(): List<Post> {
        val recipesList: MutableList<Post> = mutableListOf()
        val result = firebaseFirestore.collection("posts").orderBy("dateCreation", Query.Direction.DESCENDING).get().await()

        for (item in result) {
            recipesList.add(item.toObject(Post::class.java))
        }

        return recipesList
    }

    private suspend fun getPost(title: String): String {
        var post: String = ""
        firebaseFirestore.collection("posts").whereEqualTo("title", title).get().continueWith {
            post =  it.result.first().id
        }.await()

        return post
    }

    override suspend fun setFavourite(userUid: String, title: String, postList: List<Post>) {
        val id = getPost(title = title)
        val currentDate: String = sdf.format(Date())

        val favourites = postList.first { it.title == title }.postFavourites
        if (favourites.any{ it.user == userUid}) {
            favourites.removeAll { it.user == userUid }
        } else {
            favourites.add(PostFavourite(user = userUid, date = currentDate))
        }

        firebaseFirestore.collection("posts").document(id).update("postFavourites", favourites).await()
    }


}