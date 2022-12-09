package com.los3molineros.lyophilization_world.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.los3molineros.lyophilization_world.BuildConfig
import com.los3molineros.lyophilization_world.R
import com.los3molineros.lyophilization_world.common.AppConstants
import com.los3molineros.lyophilization_world.data.model.Post
import com.los3molineros.lyophilization_world.ui.composables.DrawerContentApp
import com.los3molineros.lyophilization_world.ui.composables.FloatingButton
import com.los3molineros.lyophilization_world.ui.composables.PostTopBar
import com.los3molineros.lyophilization_world.ui.composables.PostUI
import com.los3molineros.lyophilization_world.ui.theme.Lyophilization_worldTheme
import com.los3molineros.lyophilization_world.ui.viewModels.PostViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun PostActivity(
    onBackClick: () -> Unit = {}
) {
    Lyophilization_worldTheme {
        val context = LocalContext.current
        val viewModel = koinViewModel<PostViewModel>()
        val postList : List<Post> = listOf()

        // Lateral panel
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()

        Scaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier.fillMaxSize(1f),
            floatingActionButton = { FloatingButton() },
            isFloatingActionButtonDocked = true,
            topBar = {
                PostTopBar(
                    title = stringResource(id = R.string.app_name),
                    // Opening and closing lateral panel
                    onLeadingClick = {
                        scope.launch {
                            scaffoldState.drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    },
                    leading = null,
                    imageLeading = viewModel.firebaseUserState.value?.photo,
                    leadModifier = Modifier
                        .padding(start = 5.dp)
                        .size(50.dp)
                        .clip(shape = CircleShape)
                        .border(1.dp, Color.LightGray, CircleShape)
                )
            },
            drawerContent = { DrawerContentApp(
                onContactClick = { contactUs(context) },
                onRateClick = { rateUs(context) },
                onSignOutClick = {
                    signOut(context)
                    onBackClick()
                }
            )},
            content = {
                LazyColumn {
                    items(items = viewModel.postListState.value, itemContent = { item ->
                        PostUI(post = item, comments = R.drawable.comment, favourite = false, adminUser = false)
                    })
                }
            } )
    }

}

private fun contactUs(context: Context) {
    val goToMarket = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(AppConstants.CONTACT_US)
    )
    context.startActivity(goToMarket)
}


private fun rateUs(context: Context) {
    val goToMarket = Intent(
        Intent.ACTION_VIEW,
        Uri.parse("${AppConstants.APP_STORE}${BuildConfig.APPLICATION_ID}")
    )
    context.startActivity(goToMarket)
}

private fun signOut(context: Context) {
    Firebase.auth.signOut()
    GoogleSignIn
        .getClient(
            context,
            GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .build()
        )
        .signOut()
}

