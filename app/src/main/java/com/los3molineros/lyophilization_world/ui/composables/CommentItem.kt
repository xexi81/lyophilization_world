package com.los3molineros.lyophilization_world.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.los3molineros.lyophilization_world.R
import com.los3molineros.lyophilization_world.data.model.PostComment
import com.los3molineros.lyophilization_world.ui.theme.Lyophilization_worldTheme

@Composable
fun CommentItem(
    principal: Boolean = true,
    comment: PostComment,
    onResponseClick: (String) -> Unit = {}
) {
    Lyophilization_worldTheme() {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = if (principal) 5.dp else 55.dp)
                .padding(bottom = 10.dp)
            ,
            verticalArrangement = Arrangement.Top,
        ) {
            Row {
                Image(
                    if (comment.userPhoto == null) {
                        painterResource(id = R.drawable.profile)
                    } else {
                        rememberImagePainter(comment.userPhoto)
                    },
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .size(if (principal) 50.dp else 35.dp)
                        .clip(shape = CircleShape)
                        .border(1.dp, Color.LightGray, CircleShape)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .background(
                            color = Color.Gray,
                            shape = RoundedCornerShape(
                                topStart = 0.dp,
                                topEnd = 5.dp,
                                bottomEnd = 5.dp,
                                bottomStart = 5.dp
                            )
                        )
                ) {
                    Text(
                        text = comment.date,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 5.dp)
                            .padding(top = 1.dp)
                            .align(Alignment.TopStart),
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.subtitle1,
                        color = Color.White
                    )


                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                            .padding(bottom = 5.dp)
                            .padding(horizontal = 5.dp)
                            .align(Alignment.TopStart)) {

                        Text(
                            text = comment.userName,
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.h2,
                            color = Color.White,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Divider(
                            thickness = 1.dp,
                            color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp)
                        )

                        Text(
                            text = comment.comment,
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Start,
                            style = MaterialTheme.typography.body1,
                            color = Color.White
                        )
                    }
                }
            }

            /*if (principal) {
                Text(
                    text = "Responder",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 15.dp)
                        .padding(top = 5.dp)
                        .padding(bottom = 10.dp)
                        .clickable { onResponseClick(comment.uuid) },
                    textAlign = TextAlign.End
                )

                comment.responses.forEach {
                    CommentItem(
                        principal = false,
                        comment = it
                    )
                }
            } */
        }
    }
}




@Preview(showBackground = true, showSystemUi = false)
@Composable
fun CommentPreview() {
    Column() {
        CommentItem(
            principal = true,
            comment = PostComment(
                user = "adsfkjadslfkj",
                userName = "Sergio Nuñez",
                userPhoto = "https://lh3.googleusercontent.com/a/AEdFTp5OkD-FohOZ0tmcNDkSI-f8Q_Z4xPUDb077lBCtRg=s96-c",
                date = "vie., 9 dic. 2022",
                comment = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"
            )
        )

        CommentItem(
            principal = false,
            comment = PostComment(
                user = "adsfkjadslfkj",
                userName = "Sergio Nuñez",
                userPhoto = "https://lh3.googleusercontent.com/a/AEdFTp5OkD-FohOZ0tmcNDkSI-f8Q_Z4xPUDb077lBCtRg=s96-c",
                date = "vie., 9 dic. 2022",
                comment = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum"
            )
        )
    }

}