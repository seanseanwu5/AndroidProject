package com.example.myapplication.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.myapplication.FbViewModel
import com.example.myapplication.R
import kotlinx.coroutines.launch

@Composable
fun SuccessScreen(navController: NavController, vm: FbViewModel) {
    val coroutineScope = rememberCoroutineScope()

    // Evilmoon
    val evilmoonComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.evilmoon))
    val evilmoonProgress by animateLottieCompositionAsState(
        composition = evilmoonComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        restartOnPlay = false
    )

    // Bus
    val crossComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.cross))
    val crossProgress by animateLottieCompositionAsState(
        composition = crossComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        restartOnPlay = false
    )

    // brain
    val showTooltip = remember { mutableStateOf(false) }
    val brainComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.brain))
    val brainProgress by animateLottieCompositionAsState(
        composition = brainComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        restartOnPlay = false
    )


    // stop
    val showStopTooltip = remember { mutableStateOf(false) }
    val stopComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.stop))
    val stopProgress by animateLottieCompositionAsState(
        composition = stopComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        restartOnPlay = false
    )


    // Painter
    val backgroundImage: Painter = painterResource(id = R.drawable.sus)
    Box(modifier = Modifier.fillMaxSize()) {
        // 背景圖片
        Image(
            painter = backgroundImage,
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        // Column
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

///////////////////////////////////////////////////////////////////////////////
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "長按下方圖片會有說明哦",
                fontSize = 25.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
///////////////////////////////////////////////////////////////////////////////

            // evilmoon
            LottieAnimation(
                composition = evilmoonComposition,
                progress = evilmoonProgress,
                modifier = Modifier
                    .size(200.dp)
                    .padding(vertical = 16.dp)
                    .clickable { navController.navigate("meme") }
            )

            Spacer(modifier = Modifier.height(12.dp))

///////////////////////////////////////////////////////////////////////////////

            Row(
                modifier = Modifier.fillMaxWidth(), // Row填滿最大寬度
                horizontalArrangement = Arrangement.SpaceEvenly // 內部元素平均間隔排列
            ) {
                // brain
                LottieAnimation(
                    composition = brainComposition,
                    progress = brainProgress,
                    modifier = Modifier
                        .size(200.dp)
                        .padding(top = 1.dp)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onLongPress = {
                                    coroutineScope.launch {
                                        showTooltip.value = true // 显示tooltip
                                    }
                                },
                                onTap = {
                                    navController.navigate("game")
                                }
                            )
                        }
                )
                if (showTooltip.value) {
                    AlertDialog(
                        onDismissRequest = { showTooltip.value = false },
                        title = { Text("遊戲介紹") },
                        text = {
                            Text(
                                "這個遊戲叫做「尋寶記憶大挑戰」，是一款好玩又能訓練記憶力的翻牌遊戲喔！在遊戲中找要尋找一對一對相同的寶藏卡片。所有卡片一開始都是背面朝上，每張卡片翻過來後都有漂亮的圖案，要找到相同的圖案才能成功配對，記得好好用你的小腦袋瓜記住每張卡片上的圖案和位置喔！",
                                style = androidx.compose.ui.text.TextStyle(fontSize = 20.sp) // 可以調整數值以改變大小
                            )
                        },
                        confirmButton = { }
                    )
                }
                LottieAnimation(
                    composition = crossComposition,
                    progress = crossProgress,
                    modifier = Modifier
                        .size(200.dp)
                        .padding(top = 16.dp)
                        .clickable { navController.navigate("cross") }
                )
                Spacer(Modifier.weight(1f))
            }
            LottieAnimation(
                composition = stopComposition,
                progress = stopProgress,
                modifier = Modifier
                    .size(150.dp)
                    .padding(top = 16.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onLongPress = {
                                coroutineScope.launch {
                                    showStopTooltip.value = true
                                }
                            },
                            onTap = {
                                navController.navigate("piano")
                            }
                        )
                    }
            )

            if (showStopTooltip.value) {
                AlertDialog(
                    onDismissRequest = { showStopTooltip.value = false },
                    title = { Text("小朋友，看到這個標誌絕對不要去點擊喔") },
                    confirmButton = {
                    }
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}