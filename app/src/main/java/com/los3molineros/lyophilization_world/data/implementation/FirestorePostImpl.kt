package com.los3molineros.lyophilization_world.data.implementation

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import com.los3molineros.lyophilization_world.data.model.*
import com.los3molineros.lyophilization_world.data.repositories.FirestorePostsRepository
import com.los3molineros.lyophilization_world.ui.composables.CommentItem
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

    private suspend fun getCompletePost(title: String): PostResponse {
        var postId: String = ""
        var post: Post = Post()

        firebaseFirestore.collection("posts").whereEqualTo("title", title).get().continueWith {
            postId =  it.result.first().id
            post = it.result.first().toObject(Post::class.java)
        }.await()

        return PostResponse(postId, post)
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

    override suspend fun setComment(user: User, title: String, comment: String): Post {
        val postResponse = getCompletePost(title)
        val currentDate: String = sdf.format(Date())

        val newComment = PostComment(
            user = user.uid.orEmpty(),
            userName = user.name.orEmpty(),
            userPhoto = user.photo.orEmpty(),
            date = currentDate,
            comment = comment,
            responses = listOf())


        postResponse.post.postComments.add(newComment)

        firebaseFirestore
            .collection("posts")
            .document(postResponse.id)
            .update("postComments", postResponse.post.postComments).await()

        return postResponse.post
    }

}