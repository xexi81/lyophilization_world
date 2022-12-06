package com.los3molineros.lyophilization_world.ui.composables

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.los3molineros.lyophilization_world.R
import com.los3molineros.lyophilization_world.ui.theme.Purple500
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CameraApp(
    directory: File? = null,
    onPhotoDone: (Uri?) -> Unit = {},
    onBackClick: () -> Unit = {}
)
{
    val context = LocalContext.current
    val lifeCycleOwner = LocalLifecycleOwner.current


    SimpleCameraPreview(
        context = context,
        lifeCycleOwner = lifeCycleOwner,
        outputDirectory = directory,
        onMediaCaptured = { onPhotoDone(it) },
        onBackClick = { onBackClick() }
    )
}

@Composable
fun SimpleCameraPreview(
    context: Context,
    lifeCycleOwner: LifecycleOwner,
    outputDirectory: File? = null,
    onMediaCaptured: (Uri?) -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context)}
    val cameraProvider = cameraProviderFuture.get()
    var imageCapture: ImageCapture? by remember { mutableStateOf(null)}
    var preview by remember { mutableStateOf<Preview?>(null) }
    val executor = ContextCompat.getMainExecutor(context)

    var lensFacing by remember { mutableStateOf(CameraSelector.LENS_FACING_BACK)}
    var flashEnabled by remember { mutableStateOf(false)}

    lateinit var cameraControl: CameraControl
    lateinit var cameraInfo: CameraInfo
    lateinit var cameraResponse: CameraResponse


    Box (modifier = Modifier
        .fillMaxWidth(1f)
        .height(300.dp)
        .border(BorderStroke(1.dp, Color.LightGray)))
        {
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RectangleShape),
            factory = {
                val previewView = PreviewView(context)
                cameraProviderFuture.addListener({
                    ImageAnalysis.Builder()
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build()
                        .apply {
                            setAnalyzer(executor, FaceAnalyzer())
                        }

                    imageCapture = ImageCapture.Builder()
                        .setTargetRotation(previewView.display.rotation)
                        .build()

                    cameraResponse = buildCamera(
                        cameraSelection = CameraSelector.LENS_FACING_BACK,
                        enableTorch = flashEnabled,
                        cameraProvider = cameraProvider,
                        lifeCycleOwner = lifeCycleOwner,
                        imageCapture = imageCapture,
                        preview = preview
                    )

                    cameraControl = cameraResponse.cameraControl
                    cameraInfo = cameraResponse.cameraInfo

                }, executor)
                preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }
                previewView
            }
        )


        // Back arrow
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .align(Alignment.TopStart)
        ) {
            IconButton(onClick = {
                onBackClick()
            }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "back arrow", tint = MaterialTheme.colors.surface)
            }
        }

        // Box with flash, turn camera and takea photo
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .clip(RoundedCornerShape(15.dp))
                .background(
                    MaterialTheme.colors.primaryVariant, RoundedCornerShape(15.dp)
                )
                .padding(8.dp)
                .align(Alignment.BottomCenter)
        ) {
            IconButton(onClick = {
                try {
                    if (cameraInfo.hasFlashUnit()) {
                        flashEnabled = !flashEnabled
                        cameraControl.enableTorch(flashEnabled)
                    }
                } catch (e: Exception) {}
            }) {
                Icon(
                    painter = painterResource(id = if (flashEnabled) R.drawable.ic_flash_on else R.drawable.ic_flash_off),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp),
                    tint = MaterialTheme.colors.surface
                )
            }

            Button(onClick = {
                val imgCapture = imageCapture ?: return@Button
                val photoFile = File(
                    outputDirectory,
                    SimpleDateFormat("yyyyMMDD-HHmmss", Locale.US)
                        .format(System.currentTimeMillis()) + ".jpg"
                )


                val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
                imgCapture.takePicture(
                    outputOptions,
                    executor,
                    object : ImageCapture.OnImageSavedCallback {
                        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                            onMediaCaptured(Uri.fromFile(photoFile))
                        }

                        override fun onError(exception: ImageCaptureException) {
                            Toast.makeText(context, "Something went wrong - 2", Toast.LENGTH_SHORT).show()
                        }
                    }
                )

            },
                modifier = Modifier
                    .size(40.dp)
                    .background(Purple500, CircleShape)
                    .shadow(4.dp, CircleShape)
                    .clip(
                        CircleShape
                    )
                    .border(5.dp, Color.LightGray, CircleShape),
                colors = ButtonDefaults.buttonColors( backgroundColor = MaterialTheme.colors.primaryVariant)
            ) {

            }

            IconButton(onClick = {
                lensFacing = if (lensFacing == CameraSelector.LENS_FACING_BACK) CameraSelector.LENS_FACING_FRONT else CameraSelector.LENS_FACING_BACK

                cameraResponse = buildCamera(
                    cameraSelection = lensFacing,
                    enableTorch = flashEnabled,
                    cameraProvider = cameraProvider,
                    lifeCycleOwner = lifeCycleOwner,
                    imageCapture = imageCapture,
                    preview = preview
                )

                cameraControl = cameraResponse.cameraControl
                cameraInfo = cameraResponse.cameraInfo
            }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_flip_camera),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp),
                    tint = MaterialTheme.colors.surface
                )
            }
        }
    }


}

private fun buildCamera(
    cameraSelection: Int,
    enableTorch: Boolean,
    cameraProvider: ProcessCameraProvider,
    lifeCycleOwner: LifecycleOwner,
    imageCapture: ImageCapture?,
    preview: Preview?
): CameraResponse {
    val cameraSelector = CameraSelector.Builder()
        .requireLensFacing(cameraSelection)
        .build()

    cameraProvider.unbindAll()

    val build = cameraProvider.bindToLifecycle(
        lifeCycleOwner,
        cameraSelector,
        imageCapture,
        preview)

    val cameraControl = build.cameraControl
    val cameraInfo = build.cameraInfo

    cameraControl.enableTorch(enableTorch)

    return CameraResponse(cameraSelector, cameraControl, cameraInfo)
}


data class CameraResponse(
    val cameraSelector: CameraSelector,
    val cameraControl: CameraControl,
    val cameraInfo: CameraInfo
)

private class FaceAnalyzer(): ImageAnalysis.Analyzer {
    @SuppressLint("UnsafeOptInUsageError")
    override fun analyze(image: ImageProxy) {
        val imagePic = image.image
        imagePic?.close()
    }
}


@androidx.compose.ui.tooling.preview.Preview(showBackground = true, showSystemUi = true)
@Composable
fun CameraPreview() {
    CameraApp()
}