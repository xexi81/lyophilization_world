package com.los3molineros.lyophilization_world.data.repositories

import com.los3molineros.lyophilization_world.data.model.Post
import com.los3molineros.lyophilization_world.data.model.User

interface FirestorePostsRepository {
    suspend fun getPosts(): List<Post>
    suspend fun setFavourite(userUid: String, title: String, postList: List<Post>)
    suspend fun setComment(user: User, title: String, comment: String): Post
}