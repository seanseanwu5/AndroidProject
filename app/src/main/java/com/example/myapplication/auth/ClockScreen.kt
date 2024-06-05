package com.example.myapplication.auth

import android.content.Context
import android.media.MediaPlayer
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*
import androidx.compose.ui.platform.LocalContext

@Composable
fun shakeEffect(): Float {
    val infiniteTransition = rememberInfiniteTransition()
    val shakeAnim = infiniteTransition.animateFloat(
        initialValue = -1f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 300
                -1f at 0 with LinearEasing
                1f at 100 with LinearEasing
                -1f at 200 with LinearEasing
            },
            repeatMode = RepeatMode.Reverse
        )
    )
    return shakeAnim.value
}

fun setupMediaPlayerAndVibrate(context: Context): MediaPlayer? {
    val mediaPlayer = try {
        MediaPlayer.create(context, R.raw.sound)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }

    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        val vibrationEffect = VibrationEffect.createOneShot(3000, VibrationEffect.DEFAULT_AMPLITUDE)
        vibrator.vibrate(vibrationEffect)
    } else {
        vibrator.vibrate(3000)
    }

    return mediaPlayer
}
@Composable
fun ClockScreen() {
    val shake = shakeEffect()
    val context = LocalContext.current
    val currentTime = rememberTime()
    val showImage = remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (showImage.value) 5f else 0f)

    LaunchedEffect(Unit) {
        delay(2800)
        setupMediaPlayerAndVibrate(context)?.start()
        showImage.value = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = currentTime,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.weight(1f))

        AnimatedVisibility(visible = showImage.value, enter = fadeIn() + scaleIn(), exit = fadeOut() + scaleOut()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.hr),
                    contentDescription = "hr image",
                    modifier = Modifier
                        .graphicsLayer {
                            translationX = shake * 10f
                        }
                        .scale(scale)
                )
            }
        }
    }
}

@Composable
private fun rememberTime(): String {
    val currentTime = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        while (true) {
            val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            val currentDateTime = dateFormat.format(Date())
            currentTime.value = currentDateTime
            delay(1000)
        }
    }

    return currentTime.value
}

fun setupMediaPlayer(context: Context): MediaPlayer? {
    return try {
        MediaPlayer.create(context, R.raw.sound)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}
