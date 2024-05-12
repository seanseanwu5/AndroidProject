package com.example.myapplication.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimatable
import androidx.compose.runtime.getValue
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.myapplication.DestinationScreen
import com.example.myapplication.FbViewModel
import com.example.myapplication.R
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*


@Composable
fun MainScreen(navController: NavController, vm: FbViewModel) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.welcome))
    Box(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.ms),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        // Foreground column with contents
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp)
        ) {

            LottieAnimation(
                composition,
                modifier = Modifier.size(300.dp), // 设置动画的大小
                iterations = LottieConstants.IterateForever // 设置动画播放的次数，LottieConstants.IterateForever 表示无限循环播放
            )
//            Image(
//                painter = painterResource(id = R.drawable.lu),//我這裡想要換成welcome動畫raw檔案，檔案名稱叫做welcome.json
//                contentDescription = null,
//                modifier = Modifier.size(200.dp)
//            )
            Text(
                text = "Welcome",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp
            )
            Spacer(modifier = Modifier.height(80.dp))

            // Box with a gradient background
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(Color(0xFFADD8E6), Color.White, Color(0xFFADD8E6))
                        )
                    )
            ) {
                Button(onClick = { navController.navigate(DestinationScreen.Signup.route)
                },
                    colors = ButtonDefaults.buttonColors(
                        Color.Transparent
                    ),
                    modifier = Modifier.width(300.dp)
                ) {
                    Text(
                        text = "註冊",
                        color = Color.Black,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp))
                    .background(
                        brush = Brush.horizontalGradient(
                            listOf(Color(0xFFADD8E6), Color.White, Color(0xFFADD8E6))
                        )
                    )
            ) {
                Button(onClick = { navController.navigate(DestinationScreen.Login.route)
                },
                    colors = ButtonDefaults.buttonColors(
                        Color.Transparent
                    ),
                    modifier = Modifier.width(300.dp)
                ) {
                    Text(
                        text = "登入",
                        color = Color.Black,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}