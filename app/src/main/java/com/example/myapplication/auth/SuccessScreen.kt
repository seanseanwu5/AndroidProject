package com.example.myapplication.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.FbViewModel
import com.example.myapplication.R
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.*


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

    // 背景图相关的Painter
    val backgroundImage: Painter = painterResource(id = R.drawable.sus)
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = backgroundImage,
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // evilmoon动画的LottieAnimation组件
            LottieAnimation(
                composition = evilmoonComposition,
                progress = evilmoonProgress,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .size(200.dp)
                    .clickable {
                        navController.navigate("piano")  // 点击跳转到下一个页面
                    }
            )

            Spacer(modifier = Modifier.height(32.dp))

            //////////////////////////////////////////////////////////////
            Column(       // 创建一个列布局，内部的元素会垂直排列
                horizontalAlignment = Alignment.CenterHorizontally,   // 列布局中的元素水平居中
                modifier = Modifier.fillMaxSize()   // 列布局会填充其父组件的全部空间
            ) {
                Box(       // 创建一个盒子布局，内部的元素会重叠
                    Modifier       // 使用修饰器自定义该盒子布局的特性
                        .weight(1f)   // 盒子布局的权重为1，以决定它在父组件中所占的空间比例
                        .fillMaxWidth(),  // 盒子布局会填充其父组件的全部宽度
                    contentAlignment = Alignment.BottomStart   // 确保盒子布局中的元素对齐到左下角
                ) {
                    LottieAnimation(    // 创建一个Lottie动画
                        composition = busComposition,   // 指定动画所使用的动画剪辑
                        progress = busProgress,  // 设置动画的播放进度
                        modifier = Modifier   // 使用修饰器自定义该动画的特性
                            .size(150.dp)   // 设置动画的尺寸为150独立像素
                            .padding(start = 20.dp)  // 在动画左边添加20独立像素的内边距
                            .offset(y = -70.dp)  // 在Y軸方向上向上偏移50dp，使动画向上移动
                            .clickable {    // 设置动画为可点击的，点击后执行的操作是导航到"bus"路径
                                navController.navigate("bus")
                            }
                    )
                }

                Spacer(Modifier.weight(2f)) // 创建一个间隙，大小由权重决定，权重为2，间隙会占用父组件剩余空间的2/(1+2)=2/3。
            }
            //////////////////////////////////////////////////////////////////
        }
    }
}
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