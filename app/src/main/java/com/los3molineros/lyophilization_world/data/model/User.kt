package com.los3molineros.lyophilization_world.data.model

data class User (
    val uid: String? = "",
    val name: String? = "",
    val email: String? = "",
    val provider: String? = "",
    val photo: String? = null
)