package com.los3molineros.lyophilization_world.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.los3molineros.lyophilization_world.R
import com.los3molineros.lyophilization_world.ui.composables.ButtonApp
import com.los3molineros.lyophilization_world.ui.composables.EditTextApp
import com.los3molineros.lyophilization_world.ui.composables.TopBarApp
import com.los3molineros.lyophilization_world.ui.theme.Lyophilization_worldTheme
import com.los3molineros.lyophilization_world.ui.viewModels.LoginWithEmailViewModel
import com.los3molineros.lyophilization_world.ui.viewModels.SplashScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginWithEmailActivity(
    onBackPressed: () -> Unit = {}
) {

    val context = LocalContext.current
    val viewModel = koinViewModel<LoginWithEmailViewModel>()

    Lyophilization_worldTheme() {
        Scaffold(
            topBar = {
                TopBarApp(
                    startIcon = R.drawable.ic_back_arrow,
                    title = context.getString(R.string.login_email),
                    endIcon = null,
                    onStartIconClick = { onBackPressed() }
                )
            }
        ) {
            Column(
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Top
            ) {
                EditTextApp(
                    placeHolder = context.getString(R.string.email),
                )

                Spacer(modifier = Modifier.height(30.dp))

                EditTextApp(
                    placeHolder = context.getString(R.string.password),
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .padding(top = 40.dp)
                ) {
                    ButtonApp(
                        modifier = Modifier
                            .weight(1f)
                            .padding(10.dp),
                        textButton = context.getString(R.string.sign_in),
                        onClickButton = {},
                        isTextButton = false,
                    )

                    ButtonApp(
                        modifier = Modifier
                            .weight(1f)
                            .padding(10.dp),
                        textButton = context.getString(R.string.sign_out),
                        onClickButton = {},
                        isTextButton = false,
                    )
                }

                ButtonApp(
                    modifier = Modifier.fillMaxWidth(),
                    textButton = context.getString(R.string.remember_password),
                    onClickButton = {},
                    isTextButton = true
                )

            }
        }
    }
}





@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginWithEmailPreview() {
    LoginWithEmailActivity()
}