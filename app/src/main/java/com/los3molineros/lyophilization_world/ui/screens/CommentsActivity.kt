package com.los3molineros.lyophilization_world.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.los3molineros.lyophilization_world.R
import com.los3molineros.lyophilization_world.data.model.Post
import com.los3molineros.lyophilization_world.data.model.User
import com.los3molineros.lyophilization_world.ui.composables.CommentItem
import com.los3molineros.lyophilization_world.ui.composables.EnterComment
import com.los3molineros.lyophilization_world.ui.composables.TopBarApp
import com.los3molineros.lyophilization_world.ui.viewModels.CommentViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CommentsActivity(
    post: Post?,
    onBackClick: () -> Unit = {}
) {
    val context = LocalContext.current
    val viewModel = koinViewModel<CommentViewModel>()
    val keyboardController = LocalSoftwareKeyboardController.current


    val user: User? by viewModel.firebaseUserState.collectAsState()
    val postState: Post? by viewModel.postState.collectAsState()

    viewModel.setPost(post)

    // Lateral panel
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(1f),
        topBar = { TopBarApp(
            startIcon = R.drawable.ic_back_arrow,
            title = postState?.title.orEmpty(),
            onStartIconClick = { onBackClick() }
        )
        },
        content = {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                Box(modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()) {
                    Image(
                        painter = rememberImagePainter(postState?.image),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(5.dp)
                    )
                }

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                )
                {
                        postState?.let {
                            LazyColumn {
                                items(items = it.postComments, itemContent = { item ->
                                    CommentItem(
                                        principal = true,
                                        comment = item,
                                        onResponseClick = {}
                                    )
                                })
                            }
                        }
                }

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color.Black)
                ) {
                    EnterComment(
                        userPhoto = user?.photo,
                        placeHolder = context.getString(R.string.comentario_placeholder),
                        onValueChanged = {comment -> viewModel.comment = comment},
                        releaseComment = {
                            keyboardController?.hide()
                            viewModel.setComment()
                        }
                    )
                }
            }
        }
    )
}