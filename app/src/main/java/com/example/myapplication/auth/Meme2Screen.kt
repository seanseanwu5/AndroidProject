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
fun Meme2Screen(navController: NavController) {
    val context = LocalContext.current
    val videoView = remember { VideoView(context) }

    // Move DisposableEffect to be outside of AndroidView creation, but still inside Composable context
    DisposableEffect(key1 = videoView) {
        videoView.start() // Start video automatically
        onDispose {
            videoView.stopPlayback() // Stop video playback on dispose
        }
    }

    AndroidView(
        factory = { videoView },
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                // Adjust the scale factor to zoom in the video. For example, 1.2f means 120% of the original size.
                scaleX = 1f
                scaleY = 1f
            }
    ) { view ->
        val videoUri = "android.resource://${context.packageName}/${R.raw.meme2}"
        view.setVideoURI(android.net.Uri.parse(videoUri))
        view.setOnCompletionListener {
            // Video playback is complete, pop backStack to return to the previous screen
            navController.popBackStack()
        }
    }
}