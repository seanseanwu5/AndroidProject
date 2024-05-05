package com.example.myapplication.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ClockScreen() {
    val currentTime = rememberTime()

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
    }
}

@Composable
private fun rememberTime(): String {
    val currentTime = remember {
        mutableStateOf("")
    }

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
