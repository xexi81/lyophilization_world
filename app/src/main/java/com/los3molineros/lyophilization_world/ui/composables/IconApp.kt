package com.los3molineros.lyophilization_world.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.los3molineros.lyophilization_world.R

@Composable
fun IconApp(
    imageVector: ImageVector? = null,
    resource: Int? = null,
    size: Dp = 50.dp,
    tint: Color = Color.Transparent,
    backgroundColor: Color = Color.Transparent,
    isCircleShape: Boolean = false,
    isBadgeBox: Boolean = false,
    badgeText: String = "",
    iconClicked: () -> Unit = {}
) {
    if (isBadgeBox) {
        BadgedBox(
            badge = { Badge { Text(badgeText) } },
            content = { ImageIcon(imageVector, resource, size, tint)} ,
            modifier = Modifier.clickable { iconClicked() }
        )
    } else
    {
        IconButton(
            modifier = Modifier
                .size(size)
                .padding(1.dp)
                .then(
                    if (isCircleShape)
                        Modifier
                            .border(
                                width = 0.dp,
                                color = Color.Gray,
                                shape = CircleShape)
                            .background(
                                backgroundColor,
                                shape = CircleShape)
                    else Modifier
                ),
            onClick = { iconClicked() },
            content = { ImageIcon(imageVector, resource, size, tint) }
        )
    }
}

@Composable
private fun ImageIcon(
    imageVector: ImageVector? =  null,
    resource: Int? = null,
    size: Dp = 50.dp,
    tint: Color = Color.Transparent
) {
    if (imageVector != null)
        Icon(
            modifier = Modifier.size(size),
            imageVector = imageVector,
            contentDescription = null,
            tint = tint,
        )
    else if (resource != null)
        Icon(
            modifier = Modifier.size(size),
            painter = painterResource(resource),
            contentDescription = null,
            tint = tint,
        )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PostComments() {
    IconApp(
        imageVector = null,
        resource = R.drawable.comment,
        size = 70.dp,
        tint = Color.Black,
        isCircleShape = false,
        isBadgeBox = true,
        iconClicked = {}
    )
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun ProfileIconPreview() {
    IconApp(
        imageVector = null,
        resource = R.drawable.profile,
        size = 70.dp,
        tint = Color.Gray,
        isCircleShape = false,
        iconClicked = {}
    )
}


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun HeartIcon() {
    IconApp(
        imageVector = null,
        resource = R.drawable.heart,
        size = 70.dp,
        tint = Color.Gray,
        isCircleShape = false,
        iconClicked = {}
    )
}


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun HeartIconFilled() {
    IconApp(
        imageVector = null,
        resource = R.drawable.full_heart,
        size = 70.dp,
        tint = Color.Red,
        isCircleShape = false,
        iconClicked = {}
    )
}


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun EditPost() {
    IconApp(
        imageVector = null,
        resource = R.drawable.pen,
        size = 70.dp,
        tint = Color.Gray,
        isCircleShape = false,
        iconClicked = {}
    )
}
