package com.los3molineros.lyophilization_world.data.repositories

import com.los3molineros.lyophilization_world.data.model.Post

interface FirestorePostsRepository {
    suspend fun getPosts(): List<Post>
    suspend fun setFavourite(userUid: String, title: String, postList: List<Post>)
}