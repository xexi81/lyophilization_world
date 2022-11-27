package com.los3molineros.lyophilization_world.ui.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.los3molineros.lyophilization_world.R
import com.los3molineros.lyophilization_world.common.getPostDate
import com.los3molineros.lyophilization_world.data.Post
import com.los3molineros.lyophilization_world.ui.theme.Lyophilization_worldTheme
import java.util.*

@Composable
fun PostUI(
    post: Post,
    comments: Int,
    favourite: Boolean,
    adminUser: Boolean,
    imageClicked: () -> Unit = {},
    commentClicked: () -> Unit = {},
    favouriteClicked: () -> Unit = {},
    editClicked: () -> Unit = {}
    ) {
    Lyophilization_worldTheme() {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp),
            backgroundColor = Color.White,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, Color.LightGray),
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Text(
                    text = post.date
                    .getPostDate()
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 2.dp),
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.End
                )
                Text(
                    text = post.title,
                    style = MaterialTheme.typography.h1,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Image(
                    painter = rememberImagePainter(post.photo),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clickable { imageClicked() }
                )
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 5.dp),
                    horizontalArrangement = Arrangement.Start
                )
                {
                    IconApp(
                        imageVector = null,
                        resource = R.drawable.comment,
                        size = 30.dp,
                        tint = Color.Black,
                        isCircleShape = false,
                        isBadgeBox = true,
                        badgeText = comments.toString(),
                        iconClicked = { commentClicked() }
                    )
                    IconApp(
                        imageVector = null,
                        resource = if (favourite) R.drawable.full_heart else R.drawable.heart,
                        size = 30.dp,
                        tint = if (favourite) Color.Red else Color.Gray,
                        isCircleShape = false,
                        iconClicked = { favouriteClicked() },
                        modifier = Modifier.padding(start = 20.dp)
                    )

                    if (adminUser) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            IconApp(
                                imageVector = null,
                                resource = R.drawable.pen,
                                size = 30.dp,
                                tint = Color.Gray,
                                isCircleShape = false,
                                iconClicked = { editClicked() }
                            )
                        }
                    }
                }
            }
        }
    }
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PostPreview() {
    PostUI(
        post = Post(
            title = "Reducing the Risks of Particles on the Outside of Vials",
            photo = "https://static.wixstatic.com/media/748b64_803302ef623d4708bbab871929633a41~mv2.jpg/v1/fill/w_600,h_400,al_c,q_80,enc_auto/748b64_803302ef623d4708bbab871929633a41~mv2.jpg",
            link = "https://www.lyophilizationworld.com/post/reducing-the-risks-of-particles-on-the-outside-of-vials",
            date = Date(),
            comments = listOf(),
            favourites = listOf()
        ),
        comments = 4,
        favourite = false,
        adminUser = true
    )
}