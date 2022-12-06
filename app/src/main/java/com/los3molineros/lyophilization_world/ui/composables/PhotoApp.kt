package com.los3molineros.lyophilization_world.ui.composables

import android.Manifest
import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.accompanist.permissions.rememberPermissionState
import com.los3molineros.lyophilization_world.R
import com.los3molineros.lyophilization_world.ui.theme.Lyophilization_worldTheme
import java.io.File

@OptIn(com.google.accompanist.permissions.ExperimentalPermissionsApi::class)
@Composable
fun PhotoApp(
    imageUri: Uri? = null,
) {
    val context = LocalContext.current
    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

    //States
    var image by remember { mutableStateOf(imageUri) }
    var showCameraState by remember { mutableStateOf(false)}

    // Response from gallery
    val launcher = rememberLauncherForActivityResult(contract =
    ActivityResultContracts.GetContent()) { uri: Uri? ->
        image = uri
    }

    Lyophilization_worldTheme {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            shape = RoundedCornerShape(8.dp),
            backgroundColor = Color.White,
            border = BorderStroke(1.dp, Color.LightGray),
            elevation = 8.dp,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .wrapContentHeight()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = "Post image",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.h2
                )

                if (showCameraState) {
                    CameraApp(
                        directory = getDirectory(context),
                        onPhotoDone =
                        { photo ->
                            image = photo
                            showCameraState = showCameraState.not()
                        },
                        onBackClick = {
                            showCameraState = showCameraState.not()
                        }
                    )
                } else {
                    Image(
                        painter = rememberImagePainter(image),
                        alignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .height(300.dp)
                            .border(BorderStroke(1.dp, Color.LightGray))
                            .padding(8.dp),
                        contentDescription = null,
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {
                    if (!showCameraState) {
                        Icon(
                            modifier = Modifier
                                .size(30.dp)
                                .clickable {
                                    if (!showCameraState) {
                                        cameraPermissionState.launchPermissionRequest()
                                        showCameraState = showCameraState.not()
                                    }
                                },
                            painter = painterResource(id = R.drawable.ic_camara),
                            contentDescription = null,
                            tint = Color.Gray
                        )

                        Icon(
                            modifier = Modifier
                                .size(30.dp)
                                .clickable { launcher.launch("image/*") },
                            painter = painterResource(id = R.drawable.ic_galeria),
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    }
                }
            }
        }
    }
}



// Store the capture image
private fun getDirectory(context: Context): File {
    val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
        File(it, context.resources.getString(R.string.app_name)).apply { mkdirs()}
    }

    return if (mediaDir != null && mediaDir.exists()) mediaDir else context.filesDir
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PhotoPreview() {
    PhotoApp()
}