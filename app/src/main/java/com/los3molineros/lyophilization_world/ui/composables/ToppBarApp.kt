package com.los3molineros.lyophilization_world.ui.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.los3molineros.lyophilization_world.R
import com.los3molineros.lyophilization_world.ui.theme.Lyophilization_worldTheme

@Composable
fun TopBarApp(
    startIcon: Int? = null,
    endIcon: Int? = null,
    title: String = "",
    onStartIconClick: () -> Unit = {},
    onEndIconClick: () -> Unit = {}
) {
    Lyophilization_worldTheme {
        TopAppBar(
            elevation = 5.dp,
            backgroundColor = Color.Black
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                ) {
                    startIcon?.let {
                        Icon(
                            painter = painterResource(id = startIcon),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .size(30.dp)
                                .clickable { onStartIconClick() }
                        )
                    }

                    Text(
                        modifier = Modifier
                            .padding(horizontal = 40.dp)
                            .align(Alignment.Center)
                            .fillMaxWidth(),
                        text = title,
                        style = MaterialTheme.typography.h2,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )

                    endIcon?.let {
                        Icon(
                            painter = painterResource(id = endIcon),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .size(30.dp)
                                .clickable { onEndIconClick() }
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TopBarPreview() {
    Column {
        TopBarApp(
            startIcon = R.drawable.ic_back_arrow,
            title = "Login with your email",
            endIcon = null
        )

        Spacer(modifier = Modifier.height(10.dp))

        TopBarApp(
            startIcon = R.drawable.ic_back_arrow,
            title = "Login with your emailadfasdf adfasdf asdfasdfasdf adfasdf",
            endIcon = R.drawable.ic_logout
        )

        Spacer(modifier = Modifier.height(10.dp))

        TopBarApp(
            startIcon = null,
            title = "Login with your email",
            endIcon = R.drawable.ic_logout
        )

        Spacer(modifier = Modifier.height(10.dp))

        TopBarApp(
            startIcon = null,
            title = "Login with your email",
            endIcon = null
        )
    }

}