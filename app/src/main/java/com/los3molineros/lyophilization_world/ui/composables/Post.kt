package com.los3molineros.lyophilization_world.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Post() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.White,

    ) {

    }
}




@Preview(showBackground = true)
@Composable
fun PostPreview() {
    Post()
}