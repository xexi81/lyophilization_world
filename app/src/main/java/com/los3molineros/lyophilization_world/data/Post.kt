package com.los3molineros.lyophilization_world.data

import java.util.*

data class Post(
    val title: String = "",
    val photo: String = "",
    val link: String = "",
    val date: Date = Date(),
    val comments: List<Comment> = listOf(),
    val favourites: List<Favourite> = listOf()
)

