package com.los3molineros.lyophilization_world.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.los3molineros.lyophilization_world.R
import com.los3molineros.lyophilization_world.data.model.Post
import com.los3molineros.lyophilization_world.ui.composables.CommentItem
import com.los3molineros.lyophilization_world.ui.composables.TopBarApp

@Composable
fun CommentsActivity(
    post: Post?,
    onBackClick: () -> Unit = {}
) {
    val context = LocalContext.current
    //val viewModel = koinViewModel<CommentViewModel>()


    // Lateral panel
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize(1f),
        topBar = { TopBarApp(
            startIcon = R.drawable.ic_back_arrow,
            title = "Comments",
            onStartIconClick = { onBackClick() }
        )
        },
        content = {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Red)
                )
                post?.let {
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

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(Color.Black)
                )
            }
        }
    )
}