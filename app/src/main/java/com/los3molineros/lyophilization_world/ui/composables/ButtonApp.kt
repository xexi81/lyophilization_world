package com.los3molineros.lyophilization_world.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.los3molineros.lyophilization_world.R
import com.los3molineros.lyophilization_world.ui.theme.Lyophilization_worldTheme


@Composable
fun ButtonApp(
    modifier: Modifier,
    textButton: String = "",
    onClickButton: () -> Unit = {},
    icon: Int? = null,
    isTextButton: Boolean = false,
    image: Int? = null
    ) {
    Lyophilization_worldTheme() {
        val margin = if (icon==null) 5 else 0

        if (isTextButton) {
            TextButton(onClick = { onClickButton() }) {
                Text(
                    text = textButton,
                    style = MaterialTheme.typography.body1,
                    modifier = modifier,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            Button(
                onClick = { onClickButton() },
                modifier,
                shape = RectangleShape,
                border = BorderStroke(0.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                ),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = margin.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    icon?.let {
                        Icon(
                            painter = painterResource(id = it),
                            contentDescription = null,
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    image?.let {
                        Image(
                            painterResource(id = it),
                            contentDescription = null,
                            modifier = Modifier.size(40.dp))
                    }

                    Text(
                        text = textButton,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}






@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ButtonPreview() {
    Column() {
        ButtonApp(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 20.dp),
            textButton = "Sign in",
            onClickButton = {},
            isTextButton = false
        )

        ButtonApp(
            modifier = Modifier.fillMaxWidth(),
            textButton = "Sign in",
            onClickButton = {},
            isTextButton = true
        )

        ButtonApp(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 20.dp),
            textButton = "Sign in",
            onClickButton = {},
            isTextButton = false,
            icon = R.drawable.heart
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ButtonApp(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                textButton = "Sign in",
                onClickButton = {},
                isTextButton = false,
                icon = R.drawable.heart
            )

            ButtonApp(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                textButton = "Sign in",
                onClickButton = {},
                isTextButton = false,
                icon = R.drawable.heart
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ButtonApp(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                textButton = "Sign in",
                onClickButton = {},
                isTextButton = false,
                icon = R.drawable.heart
            )

            ButtonApp(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                textButton = "Sign in",
                onClickButton = {},
                isTextButton = false,
                icon = R.drawable.heart
            )

            ButtonApp(
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                textButton = "Sign in",
                onClickButton = {},
                isTextButton = false,
                icon = R.drawable.heart
            )
        }
    }
}

