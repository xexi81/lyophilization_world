package com.los3molineros.lyophilization_world.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.los3molineros.lyophilization_world.R
import com.los3molineros.lyophilization_world.ui.composables.ButtonApp
import com.los3molineros.lyophilization_world.ui.theme.Lyophilization_worldTheme
import com.los3molineros.lyophilization_world.ui.viewModels.SplashScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashActivity(
    onLoginWithEmailClick: () -> Unit = {},
    continueToPost: () -> Unit = {}
) {
    Lyophilization_worldTheme {
        val context = LocalContext.current
        val viewModel = koinViewModel<SplashScreenViewModel>()

        Box(Modifier.fillMaxSize()) {
            Column(
                Modifier
                    .padding(top = 70.dp)
                    .align(Alignment.TopStart)
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
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .shadow(elevation = 8.dp, shape = RectangleShape, clip = true)
                            .border(BorderStroke(width = 1.dp, Color.Gray))
                            .height(200.dp)
                            .align(Alignment.Center)
                            .background(Color.LightGray)
                    )
                }
            }

            if (viewModel.timeHasGone == true && viewModel.userAlreadyLoggedState.value == true) {
                continueToPost()
            }

            if (viewModel.timeHasGone == true && viewModel.userAlreadyLoggedState.value == false) {
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(all = 30.dp)
                ) {

                    ButtonApp(
                        modifier = Modifier.height(50.dp),
                        textButton = context.getString(R.string.login_email),
                        image = R.drawable.ic_email,
                        onClickButton = { onLoginWithEmailClick() }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    ButtonApp(
                        modifier = Modifier.height(50.dp),
                        textButton = context.getString(R.string.login_google),
                        image = R.drawable.ic_google,
                        onClickButton = {}
                    )
                }
            }

            viewModel.errorState.value?.let {
                Snackbar(modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().padding(3.dp)) {
                    Text(text = it)
                }
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashPreview() {
    SplashActivity()
}