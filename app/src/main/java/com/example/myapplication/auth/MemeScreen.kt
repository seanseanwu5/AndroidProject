package com.example.myapplication.auth

import android.widget.VideoView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.myapplication.R

@Composable
fun MemeScreen(navController: NavController) {
    val context = LocalContext.current
    val videoView = remember { VideoView(context) }

    DisposableEffect(key1 = videoView) {
        videoView.start()
        onDispose {
            videoView.stopPlayback()
        }
    }

    AndroidView(
        factory = { videoView },
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                scaleX = 1.1f
                scaleY = 1.1f
            }
    ) { view ->
        val videoUri = "android.resource://${context.packageName}/${R.raw.meme}"
        view.setVideoURI(android.net.Uri.parse(videoUri))
        view.setOnCompletionListener {
            navController.popBackStack()
        }
    }
}
