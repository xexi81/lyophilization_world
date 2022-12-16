package com.los3molineros.lyophilization_world.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.los3molineros.lyophilization_world.R
import com.los3molineros.lyophilization_world.common.AppConstants
import com.los3molineros.lyophilization_world.data.model.Post
import com.los3molineros.lyophilization_world.data.model.User
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
    onBackClick: () -> Unit = {},
    onCommentClick: (Post) -> Unit = {},
    onContactUsClick: (String) -> Unit = {},
    onPostDetail: (String?) -> Unit = {}
) {
    Lyophilization_worldTheme {
        val context = LocalContext.current
        val viewModel = koinViewModel<PostViewModel>()

        val postList: List<Post> by viewModel.postListState.collectAsState()
        val user: User? by viewModel.firebaseUserState.collectAsState()

        // Lateral panel
        val scaffoldState = rememberScaffoldState()
        val scope = rememberCoroutineScope()

        Scaffold(
            scaffoldState = scaffoldState,
            modifier = Modifier.fillMaxSize(1f),
            floatingActionButton = { if (user?.admin == true) FloatingButton() },
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
                    imageLeading = user?.photo,
                    leadModifier = Modifier
                        .padding(start = 5.dp)
                        .size(50.dp)
                        .clip(shape = CircleShape)
                        .border(1.dp, Color.LightGray, CircleShape)
                )
            },
            drawerContent = { DrawerContentApp(
                onContactClick = {
                    //viewModel.contactUs(context)
                                 onContactUsClick(AppConstants.CONTACT_US)
                                 },
                onRateClick = { viewModel.rateUs(context) },
                onSignOutClick = {
                    viewModel.signOut(context)
                    onBackClick()
                }
            )},
            content = {
                LazyColumn{
                    items(items = postList, itemContent = { item ->
                        PostUI(
                            post = item,
                            comments = item.postComments.size,
                            favourite = item.postFavourites.any { it.user == viewModel.firebaseUserState.value?.uid },
                            adminUser = user?.admin ?: false,
                            imageClicked = { onPostDetail(item.link) },
                            favouriteClicked = { viewModel.favouriteClicked(item.title)},
                            commentClicked = {
                                onCommentClick(item)
                            }
                        )
                    })
                }
            } )
    }

}

