package com.los3molineros.lyophilization_world.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.los3molineros.lyophilization_world.R

// Set of Material typography styles to start with
val app_antic = FontFamily(
    Font(R.font.antic)
)


val Typography = Typography(
    h1 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        fontFamily = app_antic
    ),

    h2 = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        fontFamily = app_antic
    ),


    body1 = TextStyle(
        fontFamily = app_antic,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    ),

    subtitle1 = TextStyle(
        fontFamily = app_antic,
        fontSize = 10.sp,
        fontWeight = FontWeight.Light
    )
)