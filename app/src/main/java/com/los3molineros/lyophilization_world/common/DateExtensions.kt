package com.los3molineros.lyophilization_world.common

import java.text.SimpleDateFormat
import java.util.*

fun Date.getPostDate(): String {
    val actualDate = SimpleDateFormat("MMM dd")
    return  actualDate.format(this)
}