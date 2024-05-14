package com.example.myapplication.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.myapplication.FbViewModel
import com.example.myapplication.R

@Composable
fun SuccessScreen(navController: NavController, vm: FbViewModel) {
    // Evilmoon动画相关的记忆体
    val evilmoonComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.evilmoon))
    val evilmoonProgress by animateLottieCompositionAsState(
        composition = evilmoonComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        restartOnPlay = false
    )

    // Bus动画相关的记忆体（新增的）
    val busComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.bus))
    val busProgress by animateLottieCompositionAsState(
        composition = busComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        restartOnPlay = false
    )

    val stopComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.stop))
    val stopProgress by animateLottieCompositionAsState(
        composition = stopComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        restartOnPlay = false
    )

    val brainComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.brain))
    val brainProgress by animateLottieCompositionAsState(
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

            // evilmoon動畫
            LottieAnimation(
                composition = evilmoonComposition,
                progress = evilmoonProgress,
                modifier = Modifier
                    .size(200.dp)
                    .padding(vertical = 16.dp)
                    .clickable { navController.navigate("meme") }
            )

            Spacer(modifier = Modifier.height(32.dp))


            // stop按鈕的Lottie動畫，放在頁面頂部
            LottieAnimation(
                composition = stopComposition,
                progress = stopProgress,
                modifier = Modifier
                    .size(150.dp)
                    .padding(top = 16.dp)
                    .clickable { navController.navigate("piano") }
            )

            Spacer(modifier = Modifier.height(32.dp))

            // bus動畫
            LottieAnimation(
                composition = busComposition,
                progress = busProgress,
                modifier = Modifier
                    .size(150.dp)
                    .clickable { navController.navigate("bus") }
            )

            Spacer(Modifier.weight(1f)) // Spacer使下面的元素靠底部


            LottieAnimation(
                composition = brainComposition,
                progress = brainProgress,
                modifier = Modifier
                    .size(150.dp)
                    .padding(top = 16.dp)
                    .clickable { navController.navigate("game") }
            )

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
//AuthUtils.clearUserCredentials(context)


//@Composable
//fun SuccessScreen(navController: NavController, vm: FbViewModel) {
//    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.evilmoon))
//    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.bus))
//    val progress by animateLottieCompositionAsState(
//        composition,
//        iterations = LottieConstants.IterateForever,
//        isPlaying = true,
//        restartOnPlay = false
//    )
//    val backgroundImage: Painter = painterResource(id = R.drawable.sus)
//    Box(modifier = Modifier.fillMaxSize()) {
//        Image(
//            painter = backgroundImage,
//            contentDescription = "Background Image",
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop // 用来填充并裁剪或适应屏幕大小
//        )
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//        ) {
//            // 使用LottieAnimation替换原来的Image组件
//            LottieAnimation(
//                composition,
//                progress,
//                modifier = Modifier
//                    .padding(vertical = 16.dp)
//                    .size(200.dp) // 根据需要设定尺寸
//                    .clickable {
//                        navController.navigate("piano") // 点击动画跳转到下一个页面
//                    }
//            )
//            // 其余的Column布局内容
//            Spacer(modifier = Modifier.weight(1f))
//
////            for (i in 1..3) {
////                Row(
////                    horizontalArrangement = Arrangement.spacedBy(16.dp),
////                    modifier = Modifier.fillMaxWidth().padding(16.dp)
////                ) {
////                    Image(
////                        painter = painterResource(id = R.drawable.maria),
////                        contentDescription = "maria",
////                        modifier = Modifier
////                            .weight(1f)
////                            .size(100.dp)
////                            .clickable { /* 在这执行点击图片的操作 */ }
////                    )
////
////                    Image(
////                        painter = painterResource(id = R.drawable.maria),
////                        contentDescription = "maria",
////                        modifier = Modifier
////                            .weight(1f)
////                            .size(100.dp)
////                            .clickable { /* 在这执行点击图片的操作 */ }
////                    )
////                }
////            }
////
////            Spacer(modifier = Modifier.weight(1f))
//        }
//    }
//}

//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import com.example.myapplication.FbViewModel
//import com.example.myapplication.R // 确保这个引入匹配您项目的包名
//
//@Composable
//fun SuccessScreen(navController: NavController, vm: FbViewModel) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.fillMaxSize().padding(16.dp)
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.lu),
//            contentDescription = "lu",
//            modifier = Modifier
//                .padding(vertical = 16.dp)
//                .clickable {
//                    navController.navigate("piano")
//                }
//        )
//
//        Spacer(modifier = Modifier.weight(1f))
//
//        for (i in 1..3) {
//            Row(
//                horizontalArrangement = Arrangement.spacedBy(16.dp),
//                modifier = Modifier.fillMaxWidth().padding(16.dp)
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.maria),
//                    contentDescription = "maria",
//                    modifier = Modifier
//                        .weight(1f)
//                        .size(100.dp)
//                        .clickable { /* 在这执行点击第二张maria图片的操作 */ }
//                )
//
//                Image(
//                    painter = painterResource(id = R.drawable.maria),
//                    contentDescription = "maria",
//                    modifier = Modifier
//                        .weight(1f)
//                        .size(100.dp)
//                        .clickable { /* 在这执行点击第二张maria图片的操作 */ }
//                )
//            }
//        }
//
//        Spacer(modifier = Modifier.weight(1f))
//    }
//}