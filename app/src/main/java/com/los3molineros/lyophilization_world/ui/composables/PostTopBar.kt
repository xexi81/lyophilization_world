package com.los3molineros.lyophilization_world.ui.composables


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.los3molineros.lyophilization_world.R


@Composable
fun PostTopBar(
    title: String = "",
    onLeadingClick: () -> Unit,
    leading: ImageVector? = null,
    imageLeading: String?,
    leadModifier: Modifier = Modifier,
) {
    var showPostMenu by remember { mutableStateOf(false)}

    TopAppBar(
        backgroundColor = Color.Black
    ) {

        Row(
            modifier = Modifier.fillMaxSize(1f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leading==null) {
                Image(
                    if (imageLeading == null) {
                        painterResource(id = R.drawable.profile)
                    } else {
                        rememberImagePainter(imageLeading)
                    },
                    contentDescription = "leading",
                    contentScale = ContentScale.Crop,
                    modifier = leadModifier.clickable { onLeadingClick() }.background(MaterialTheme.colors.onPrimary)
                )
            }else {
                Icon(
                    imageVector = leading ,
                    contentDescription = "leading",
                    modifier = leadModifier
                        .fillMaxWidth(0.10f)
                        .size(40.dp)
                        .clickable { onLeadingClick() }
                )
            }

            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h1,
                color = Color.White
            )
        }
    }
}

