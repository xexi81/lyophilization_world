package com.los3molineros.lyophilization_world.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class PostComment(
    var uuid: String = UUID.randomUUID().toString(),
    var user: String = "",
    var userName: String = "",
    var userPhoto: String? = null,
    var date: String = "",
    var comment: String = "",
    var responses: List<PostComment> = listOf()
): Parcelable
