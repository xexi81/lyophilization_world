package com.los3molineros.lyophilization_world.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.los3molineros.lyophilization_world.R
import com.los3molineros.lyophilization_world.ui.theme.Lyophilization_worldTheme

@Composable
fun EnterComment(
    userPhoto:String? = null,
    placeHolder: String = "",
    onValueChanged: (String) -> Unit = {},
    releaseComment: () -> Unit = {}
) {
    Lyophilization_worldTheme {
        val inputValue = remember { mutableStateOf("") }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Box(
                Modifier.weight(0.15f)
            ) {
                Image(
                    if (userPhoto == null) {
                        painterResource(id = R.drawable.profile)
                    } else {
                        rememberImagePainter(userPhoto)
                    },
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .size(50.dp)
                        .clip(shape = CircleShape)
                        .border(1.dp, Color.LightGray, CircleShape)
                        .align(Alignment.Center)
                )
            }

            Box(
                Modifier
                    .weight(0.50f)
                    .padding(horizontal = 3.dp)
            ) {
                EditTextApp(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 3.dp),
                    placeHolder = placeHolder,
                    onValueChanged = {
                        inputValue.value = it
                        onValueChanged(inputValue.value) },
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    maxLines = 5,
                    textColor = Color.White,
                    text = inputValue.value
                )
            }

            Box(
                Modifier.weight(0.2f)
            ) {
                ButtonApp(
                    modifier = Modifier.align(Alignment.Center),
                    textButton = stringResource(id = R.string.post_comment),
                    onClickButton = {
                        releaseComment()
                        inputValue.value = "" },
                    isTextButton = true,
                    isTextBold = true,
                    textColor = Color.Gray
                )
            }

        }
    }
}



@Preview(showBackground = true, showSystemUi = false)
@Composable
fun EnterCommentPreview() {
    EnterComment(
        userPhoto = "https://lh3.googleusercontent.com/a/AEdFTp5OkD-FohOZ0tmcNDkSI-f8Q_Z4xPUDb077lBCtRg=s288-p-rw-no",
        placeHolder = "Deja tus comentarios aquí...",
        onValueChanged = {}
    )
}