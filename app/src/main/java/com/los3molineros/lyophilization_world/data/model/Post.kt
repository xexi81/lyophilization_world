package com.los3molineros.lyophilization_world.data.model

import java.util.*

data class Post(
    var title: String = "",
    var image: String? = null,
    var link: String? = null,
    var postComments: MutableList<PostComment> = mutableListOf(),
    var postFavourites: MutableList<PostFavourite> = mutableListOf(),
    var dateCreation: Date? = null,
    var user: String? = null,
    var username: String? = null,
    var userPhoto: String? = null
)

