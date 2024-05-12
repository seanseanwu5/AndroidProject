package com.example.myapplication.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.FbViewModel
import com.example.myapplication.R // 确保这个引入匹配您项目的包名

@Composable
fun SuccessScreen(navController: NavController, vm: FbViewModel) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.lu),
            contentDescription = "lu",
            modifier = Modifier
                .padding(vertical = 16.dp)
                .clickable {
                    navController.navigate("piano")
                }
        )

        Spacer(modifier = Modifier.weight(1f))

        for (i in 1..3) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.maria),
                    contentDescription = "maria",
                    modifier = Modifier
                        .weight(1f)
                        .size(100.dp)
                        .clickable { /* 在这执行点击第二张maria图片的操作 */ }
                )

                Image(
                    painter = painterResource(id = R.drawable.maria),
                    contentDescription = "maria",
                    modifier = Modifier
                        .weight(1f)
                        .size(100.dp)
                        .clickable { /* 在这执行点击第二张maria图片的操作 */ }
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}