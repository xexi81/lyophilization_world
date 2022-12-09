package com.los3molineros.lyophilization_world.ui.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FloatingButton() {
    FloatingActionButton(
        backgroundColor = Color.Black,
        onClick = {  }
    )
    {
        Icon(
            modifier = Modifier.size(45.dp),
            imageVector = Icons.Default.Add,
            tint = Color.White,
            contentDescription = null
        )
    }
}
