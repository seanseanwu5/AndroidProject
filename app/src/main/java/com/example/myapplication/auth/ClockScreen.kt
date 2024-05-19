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
                durationMillis = 300 // 抖动效果的持续时间
                -1f at 0 with LinearEasing // 从负方向开始抖动
                1f at 100 with LinearEasing // 向正方向抖动
                -1f at 200 with LinearEasing // 再次向负方向抖动
            },
            repeatMode = RepeatMode.Reverse // 动画结束后反向执行
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

    // 获取Vibrator服务
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

    // 检查是否有震动权限
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        // Android 8.0 (API 26)及以上版本
        val vibrationEffect = VibrationEffect.createOneShot(3000, VibrationEffect.DEFAULT_AMPLITUDE)
        vibrator.vibrate(vibrationEffect)
    } else {
        // 旧的API版本
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
        delay(2800) // 现在改为延后2.8秒播放音频
        setupMediaPlayerAndVibrate(context)?.start() // 播放音频
//        delay(1000)  // 再延后1秒显示图片
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
                        // 添加图层变换
                        .graphicsLayer {
                            translationX = shake * 10f // 乘以一个值来放大抖动效果
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
            delay(1000) // 每秒更新时间
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
//package com.example.myapplication.auth
//
//import androidx.compose.animation.*
//import androidx.compose.animation.core.animateFloatAsState
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.scale
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.example.myapplication.R
//import kotlinx.coroutines.delay
//import java.text.SimpleDateFormat
//import java.util.*
//import android.content.res.Resources
//import android.media.MediaPlayer
//import androidx.compose.ui.platform.LocalContext
//
//@Composable
//fun ClockScreen() {
//    val context = LocalContext.current
//    val resources = LocalContext.current.resources
//    val currentTime = rememberTime()
//    val showImage = remember { mutableStateOf(false) }
//    val scale by animateFloatAsState(if (showImage.value) 4f else 0f)
//
//    LaunchedEffect(Unit) {
//        delay(4000)
//        showImage.value = true
//        playSound(context.resources)
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = currentTime,
//            fontSize = 48.sp,
//            fontWeight = FontWeight.Bold,
//            color = Color.Black
//        )
//
//        Spacer(modifier = Modifier.weight(1f))
//
//        AnimatedVisibility(visible = showImage.value, enter = fadeIn() + scaleIn(), exit = fadeOut() + scaleOut()) {
//            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//                Image(painter = painterResource(id = R.drawable.hr),
//                    contentDescription = "hr image",
//                    modifier = Modifier.scale(scale))
//            }
//        }
//    }
//}
//
//fun playSound(resources: Resources) {
//    val mediaPlayer: MediaPlayer? = MediaPlayer.create(LocalContext.current, R.raw.sound)
//    mediaPlayer?.start()
//    mediaPlayer?.setOnCompletionListener {
//        it.release()
//    }
//}
//
//@Composable
//private fun rememberTime(): String {
//    val currentTime = remember { mutableStateOf("") }
//
//    LaunchedEffect(Unit) {
//        while (true) {
//            val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
//            val currentDateTime = dateFormat.format(Date())
//            currentTime.value = currentDateTime
//            delay(1000)
//        }
//    }
//
//    return currentTime.value
//}

//package com.example.myapplication.auth
//
//import androidx.compose.animation.*
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Box
//import androidx.compose.ui.res.painterResource
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import kotlinx.coroutines.delay
//import java.text.SimpleDateFormat
//import java.util.*
//
//@Composable
//fun ClockScreen() {
//    val currentTime = rememberTime()
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = currentTime,
//            fontSize = 48.sp,
//            fontWeight = FontWeight.Bold,
//            color = Color.Black
//        )
//    }
//}
//
//@Composable
//private fun rememberTime(): String {
//    val currentTime = remember {
//        mutableStateOf("")
//    }
//
//    LaunchedEffect(Unit) {
//        while (true) {
//            val dateFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
//            val currentDateTime = dateFormat.format(Date())
//            currentTime.value = currentDateTime
//            delay(1000)
//        }
//    }
//
//    return currentTime.value
//}

