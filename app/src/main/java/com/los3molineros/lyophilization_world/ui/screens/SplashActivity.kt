package com.los3molineros.lyophilization_world.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.los3molineros.lyophilization_world.R
import com.los3molineros.lyophilization_world.ui.theme.Lyophilization_worldTheme

@Composable
fun SplashActivity() {
    Lyophilization_worldTheme() {
        val context = LocalContext.current

        val systemUiController = rememberSystemUiController()
        systemUiController.setSystemBarsColor(color = Color.Transparent)

        Column(
            Modifier.padding(top = 70.dp)
        ) {
            Text(
                text = stringResource(id = R.string.app_name).uppercase(),
                style = MaterialTheme.typography.h1,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Box(
                Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .padding(top = 20.dp)
                    .align(Alignment.CenterHorizontally)
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.fondo),
                    contentDescription = null,
                    modifier = Modifier
                        .shadow(elevation = 8.dp, shape = RectangleShape, clip = true)
                        .border(BorderStroke(width = 1.dp,  Color.LightGray))
                        .height(153.dp)
                        .width(213.dp)
                        .align(Alignment.Center)
                        .background(Color.LightGray)
                )
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashPreview() {
    SplashActivity()
}