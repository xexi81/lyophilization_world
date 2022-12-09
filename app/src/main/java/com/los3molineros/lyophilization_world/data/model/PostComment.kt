package com.los3molineros.lyophilization_world.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostComment(
    var user: String = "",
    var userName: String = "",
    var userPhoto: String? = null,
    var date: String = "",
    var comment: String = "",
): Parcelable
