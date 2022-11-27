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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
    showCamera: Boolean = false,
    showCameraClick: () -> Unit = {},
    hideCameraClick: () -> Unit = {},
    onPhotoDoneClick: (Uri?) -> Unit = {},
    imageUri: Uri? = null,
) {
    val context = LocalContext.current
    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

    val launcher = rememberLauncherForActivityResult(contract =
    ActivityResultContracts.GetContent()) { uri: Uri? ->
        onPhotoDoneClick(uri)
    }

    Lyophilization_worldTheme {
        Column(
            Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primaryVariant)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(4.dp),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = Color.White,
                border = BorderStroke(1.dp, Color.LightGray),
                elevation = 8.dp,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Post image",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.h2
                    )

                    Divider(thickness = 1.dp, color = Color.LightGray)

                    if (showCamera) {
                        CameraApp(getDirectory(context),
                            onPhotoDone = { onPhotoDoneClick(it) },
                            onBackClick = { hideCameraClick() }
                        )
                    } else {
                        Image(
                            painter = rememberImagePainter(imageUri),
                            contentScale = ContentScale.Crop,
                            alignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .height(200.dp)
                                .padding(8.dp)
                                .border(BorderStroke(1.dp, Color.LightGray)),
                            contentDescription = null,
                        )
                    }

                    Row (
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    {
                        Icon(
                            modifier = Modifier.size(50.dp).clickable {
                                if (!showCamera) {
                                    cameraPermissionState.launchPermissionRequest()
                                    showCameraClick()
                                }                               
                            },
                            painter = painterResource(id = R.drawable.ic_camara),
                            contentDescription = null,
                            tint = Color.Gray
                        )

                        Icon(
                            modifier = Modifier.size(50.dp).clickable { launcher.launch("image/*") },
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