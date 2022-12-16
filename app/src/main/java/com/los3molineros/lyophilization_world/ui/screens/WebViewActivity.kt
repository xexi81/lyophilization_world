package com.los3molineros.lyophilization_world.ui.screens

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.los3molineros.lyophilization_world.R
import com.los3molineros.lyophilization_world.ui.composables.TopBarApp
import com.los3molineros.lyophilization_world.ui.theme.Lyophilization_worldTheme

@Composable
fun WebViewActivity(
    title: String = "",
    url: String? = "",
    onBackPressed: () -> Unit = {}
) {
    Lyophilization_worldTheme {
        Scaffold(
            topBar = {
                TopBarApp(
                    startIcon = R.drawable.ic_back_arrow,
                    title = title,
                    endIcon = null,
                    onStartIconClick = { onBackPressed() }
                )
            },
            content = {
                url?.let { newUrl ->
                    AndroidView(factory = {
                        val apply = WebView(it).apply {
                            layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                            webViewClient = WebViewClient()
                            loadUrl(newUrl)
                        }
                        apply
                    }, update = { it.loadUrl(newUrl)})
                }
            }
        )
    }
}




@Preview
@Composable
fun WebViewPreview() {
    WebViewActivity(
        url =  "https://www.marca.com",
        onBackPressed = {}
    )
}