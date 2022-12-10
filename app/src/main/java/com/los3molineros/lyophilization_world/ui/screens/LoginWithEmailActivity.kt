package com.los3molineros.lyophilization_world.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.los3molineros.lyophilization_world.R
import com.los3molineros.lyophilization_world.common.enums.FirebaseLoginErrorEnum
import com.los3molineros.lyophilization_world.ui.composables.ButtonApp
import com.los3molineros.lyophilization_world.ui.composables.EditTextApp
import com.los3molineros.lyophilization_world.ui.composables.TopBarApp
import com.los3molineros.lyophilization_world.ui.theme.Lyophilization_worldTheme
import com.los3molineros.lyophilization_world.ui.viewModels.LoginWithEmailViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginWithEmailActivity(
    onBackPressed: () -> Unit = {},
    onLoginSuccessfully: () -> Unit = {}
) {

    val context = LocalContext.current
    val viewModel = koinViewModel<LoginWithEmailViewModel>()
    val keyboardController = LocalSoftwareKeyboardController.current

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
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 16.dp),
                    placeHolder = context.getString(R.string.email),
                    onValueChanged = { viewModel.email = it }
                )

                Spacer(modifier = Modifier.height(30.dp))

                EditTextApp(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(horizontal = 16.dp),
                    placeHolder = context.getString(R.string.password),
                    onValueChanged = { viewModel.password = it}
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
                        onClickButton = {
                            keyboardController?.hide()
                            viewModel.signIn()
                        },
                        isTextButton = false,
                    )

                    ButtonApp(
                        modifier = Modifier
                            .weight(1f)
                            .padding(10.dp),
                        textButton = context.getString(R.string.sign_up),
                        onClickButton = {
                            keyboardController?.hide()
                            viewModel.signUp()
                        },
                        isTextButton = false,
                    )
                }

                ButtonApp(
                    modifier = Modifier.fillMaxWidth(),
                    textButton = context.getString(R.string.remember_password),
                    onClickButton = {
                        keyboardController?.hide()
                        viewModel.forgetPassword()
                    },
                    isTextButton = true
                )
            }

            if (viewModel.error != null) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Snackbar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomStart)
                            .padding(5.dp)
                    ) {
                        val errorToShow  =
                            when(viewModel.error) {
                                FirebaseLoginErrorEnum.MAIL_ERROR -> { R.string.email_error }
                                FirebaseLoginErrorEnum.CONNECTION_ERROR -> { R.string.connection_error }
                                FirebaseLoginErrorEnum.EMPTY_FIELDS_ERROR -> { R.string.user_pass_error}
                                FirebaseLoginErrorEnum.DEFAULT_ERROR -> { R.string.default_error}
                                FirebaseLoginErrorEnum.RESTART_OK -> { R.string.restart_pass_ok }
                                else -> { R.string.default_error}
                            }

                        Text(
                            text = stringResource(id = errorToShow),
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }

            if (viewModel.externalError != null) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Snackbar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomStart)
                            .padding(5.dp)
                    ) {
                        Text(
                            text = viewModel.externalError!!,
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }
        }
    }

    // Navigate to PostScreen
    if (viewModel.navigateToPost) {
        viewModel.restartError()
        viewModel.setNavigateToFalse()
        onLoginSuccessfully()
    }
}





@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginWithEmailPreview() {
    LoginWithEmailActivity()
}