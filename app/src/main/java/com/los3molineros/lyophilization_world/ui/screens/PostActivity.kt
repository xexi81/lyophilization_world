package com.los3molineros.lyophilization_world.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun PostActivity(
    navigateToSplash: () -> Unit = {}
) {
    Column() {
        Text(text = "Post Activity")

        Button(onClick = {
            Firebase.auth.signOut()
            navigateToSplash()
        }) {
            Text(text = "Sign out")
        }
    }
}