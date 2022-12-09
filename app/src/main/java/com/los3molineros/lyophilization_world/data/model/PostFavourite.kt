package com.los3molineros.lyophilization_world.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostFavourite(
    var user: String = "",
    var date: String = "",
): Parcelable