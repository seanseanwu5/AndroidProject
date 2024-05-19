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

    // Evilmoon动画相关的记忆体
    val evilmoonComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.evilmoon))
    val evilmoonProgress by animateLottieCompositionAsState(
        composition = evilmoonComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        restartOnPlay = false
    )

    // Bus动画相关的记忆体（新增的）
    val crossComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.cross))
    val crossProgress by animateLottieCompositionAsState(
        composition = crossComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        restartOnPlay = false
    )

    // brain动画
    val showTooltip = remember { mutableStateOf(false) }
    val brainComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.brain))
    val brainProgress by animateLottieCompositionAsState(
        composition = brainComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        restartOnPlay = false
    )


    // 狀態管理變量改成针对stop按键的
    val showStopTooltip = remember { mutableStateOf(false) }
    val stopComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.stop))
    val stopProgress by animateLottieCompositionAsState(
        composition = stopComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        restartOnPlay = false
    )


    // 背景图相关的Painter
    val backgroundImage: Painter = painterResource(id = R.drawable.sus)
    Box(modifier = Modifier.fillMaxSize()) {
        // 背景圖片
        Image(
            painter = backgroundImage,
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        // 外層Column容器
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
                fontSize = 25.sp, // 可以根据需要调整文字大小
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally) // 将文字居中对齐
            )

///////////////////////////////////////////////////////////////////////////////

            // evilmoon動畫
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
                // brain動畫
                LottieAnimation(
                    composition = brainComposition,
                    progress = brainProgress,
                    modifier = Modifier
                        .size(200.dp)
                        .padding(top = 1.dp)
                        .pointerInput(Unit) {
                            detectTapGestures(
                                onLongPress = { // 处理长按事件
                                    // 这里不需要执行showTooltip.value = false因为长按后不会立即触发其它事件
                                    coroutineScope.launch {
                                        showTooltip.value = true // 显示tooltip
                                    }
                                },
                                onTap = { // 处理点击事件
                                    navController.navigate("game") // 导航到GameScreen
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

//                Spacer(modifier = Modifier.height(32.dp))

                // cross動畫
                // cross動畫
                LottieAnimation(
                    composition = crossComposition,
                    progress = crossProgress,
                    modifier = Modifier
                        .size(200.dp)
                        .padding(top = 16.dp) // 在這裡增加了padding
                        .clickable { navController.navigate("cross") }
                )

                Spacer(Modifier.weight(1f))
            }
/////////////////////////////////////////////////////////////////////////////////////
//            Spacer(modifier = Modifier.height(32.dp))
//
//
//            if (showTooltip.value) {
//                AlertDialog(
//                    onDismissRequest = { showTooltip.value = false },
//                    title = { Text("關於這個按鈕") },
//                    text = { Text("點擊這個大腦按鈕就可以開始遊戲。") },
//                    confirmButton = { }
//                )
//            }



/////////////////////////////////////////////////////////////////////////////////////////



/////////////////////////////////////////////////////////////////////////////////////////
            // stop按鈕的Lottie動畫，放在頁面頂部
            LottieAnimation(
                composition = stopComposition,
                progress = stopProgress,
                modifier = Modifier
                    .size(150.dp)
                    .padding(top = 16.dp)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onLongPress = { // 处理长按事件
                                coroutineScope.launch {
                                    showStopTooltip.value = true // 仅显示stop按钮的tooltip
                                }
                            },
                            onTap = { // 处理点击事件
                                navController.navigate("piano") // 导航到Piano Screen
                            }
                        )
                    }
            )

            // 当showStopTooltip為true時顯示AlertDialog
            if (showStopTooltip.value) {
                AlertDialog(
                    onDismissRequest = { showStopTooltip.value = false },
                    title = { Text("小朋友，看到這個標誌絕對不要去點擊喔") },
                    // 省略了内容和按钮，根据实际需要填写
                    confirmButton = {
                        // 如果需要确认按钮的代码
                    }
                )
            }

            // 下面是其他的组件，如Spacer等
            Spacer(modifier = Modifier.height(32.dp))

            // Spacer使下面的元素靠底部

        }
    }
}




//package com.example.myapplication.auth
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.gestures.detectTapGestures
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.material3.AlertDialog
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.painter.Painter
//import androidx.compose.ui.input.pointer.pointerInput
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavController
//import com.airbnb.lottie.compose.LottieAnimation
//import com.airbnb.lottie.compose.LottieCompositionSpec
//import com.airbnb.lottie.compose.LottieConstants
//import com.airbnb.lottie.compose.animateLottieCompositionAsState
//import com.airbnb.lottie.compose.rememberLottieComposition
//import com.example.myapplication.FbViewModel
//import com.example.myapplication.R
//import kotlinx.coroutines.launch
//
//@Composable
//fun SuccessScreen(navController: NavController, vm: FbViewModel) {
//    val coroutineScope = rememberCoroutineScope()
//
//    // Evilmoon动画相关的记忆体
//    val evilmoonComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.evilmoon))
//    val evilmoonProgress by animateLottieCompositionAsState(
//        composition = evilmoonComposition,
//        iterations = LottieConstants.IterateForever,
//        isPlaying = true,
//        restartOnPlay = false
//    )
//
//    // Bus动画相关的记忆体（新增的）
//    val crossComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.cross))
//    val crossProgress by animateLottieCompositionAsState(
//        composition = crossComposition,
//        iterations = LottieConstants.IterateForever,
//        isPlaying = true,
//        restartOnPlay = false
//    )
//
//    // brain动画
//    val showTooltip = remember { mutableStateOf(false) }
//    val brainComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.brain))
//    val brainProgress by animateLottieCompositionAsState(
//        composition = brainComposition,
//        iterations = LottieConstants.IterateForever,
//        isPlaying = true,
//        restartOnPlay = false
//    )
//
//
//    // 狀態管理變量改成针对stop按键的
//    val showStopTooltip = remember { mutableStateOf(false) }
//    val stopComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.stop))
//    val stopProgress by animateLottieCompositionAsState(
//        composition = stopComposition,
//        iterations = LottieConstants.IterateForever,
//        isPlaying = true,
//        restartOnPlay = false
//    )
//
//
//    // 背景图相关的Painter
//    val backgroundImage: Painter = painterResource(id = R.drawable.sus)
//    Box(modifier = Modifier.fillMaxSize()) {
//        // 背景圖片
//        Image(
//            painter = backgroundImage,
//            contentDescription = "Background Image",
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop
//        )
//        // 外層Column容器
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//
/////////////////////////////////////////////////////////////////////////////////
//
//            Spacer(modifier = Modifier.height(30.dp))
//
//            Text(
//                text = "長按下方圖片會有說明哦",
//                fontSize = 25.sp, // 可以根据需要调整文字大小
//                color = Color.White,
//                modifier = Modifier.align(Alignment.CenterHorizontally) // 将文字居中对齐
//            )
//
/////////////////////////////////////////////////////////////////////////////////
//
//            // evilmoon動畫
//            LottieAnimation(
//                composition = evilmoonComposition,
//                progress = evilmoonProgress,
//                modifier = Modifier
//                    .size(200.dp)
//                    .padding(vertical = 16.dp)
//                    .clickable { navController.navigate("meme") }
//            )
//
//            Spacer(modifier = Modifier.height(12.dp))
//
/////////////////////////////////////////////////////////////////////////////////
//
//
//            if (showTooltip.value) {
//                AlertDialog(
//                    onDismissRequest = { showTooltip.value = false },
//                    title = { Text("關於這個按鈕") },
//                    text = { Text("點擊這個大腦按鈕就可以開始遊戲。") },
//                    confirmButton = { }
//                )
//            }
//
//            LottieAnimation(
//                composition = brainComposition,
//                progress = brainProgress,
//                modifier = Modifier
//                    .size(200.dp)
//                    .padding(top = 16.dp)
//                    .pointerInput(Unit) {
//                        detectTapGestures(
//                            onLongPress = { // 处理长按事件
//                                // 这里不需要执行showTooltip.value = false因为长按后不会立即触发其它事件
//                                coroutineScope.launch {
//                                    showTooltip.value = true // 显示tooltip
//                                }
//                            },
//                            onTap = { // 处理点击事件
//                                navController.navigate("game") // 导航到GameScreen
//                            }
//                        )
//                    }
//            )
//
//            if (showTooltip.value) {
//                AlertDialog(
//                    onDismissRequest = { showTooltip.value = false },
//                    title = { Text("遊戲介紹") },
//                    text = {
//                        Text(
//                            "這個遊戲叫做「尋寶記憶大挑戰」，是一款好玩又能訓練記憶力的翻牌遊戲喔！在遊戲中找要尋找一對一對相同的寶藏卡片。所有卡片一開始都是背面朝上，每張卡片翻過來後都有漂亮的圖案，要找到相同的圖案才能成功配對，記得好好用你的小腦袋瓜記住每張卡片上的圖案和位置喔！",
//                            style = androidx.compose.ui.text.TextStyle(fontSize = 20.sp) // 可以調整數值以改變大小
//                        )
//                    },
//                    confirmButton = { }
//                )
//            }
//
//            Spacer(modifier = Modifier.height(32.dp))
//
///////////////////////////////////////////////////////////////////////////////////////////
//
//            // cross動畫
//            LottieAnimation(
//                composition = crossComposition,
//                progress = crossProgress,
//                modifier = Modifier
//                    .size(150.dp)
//                    .clickable { navController.navigate("cross") }
//            )
//
//            Spacer(Modifier.weight(1f))
//
///////////////////////////////////////////////////////////////////////////////////////////
//            // stop按鈕的Lottie動畫，放在頁面頂部
//            LottieAnimation(
//                composition = stopComposition,
//                progress = stopProgress,
//                modifier = Modifier
//                    .size(150.dp)
//                    .padding(top = 16.dp)
//                    .pointerInput(Unit) {
//                        detectTapGestures(
//                            onLongPress = { // 处理长按事件
//                                coroutineScope.launch {
//                                    showStopTooltip.value = true // 仅显示stop按钮的tooltip
//                                }
//                            },
//                            onTap = { // 处理点击事件
//                                navController.navigate("piano") // 导航到Piano Screen
//                            }
//                        )
//                    }
//            )
//
//            // 当showStopTooltip為true時顯示AlertDialog
//            if (showStopTooltip.value) {
//                AlertDialog(
//                    onDismissRequest = { showStopTooltip.value = false },
//                    title = { Text("小朋友，看到這個標誌絕對不要去點擊喔") },
//                    // 省略了内容和按钮，根据实际需要填写
//                    confirmButton = {
//                        // 如果需要确认按钮的代码
//                    }
//                )
//            }
//
//            // 下面是其他的组件，如Spacer等
//            Spacer(modifier = Modifier.height(32.dp))
//
// // Spacer使下面的元素靠底部
//
//        }
//    }
//}
