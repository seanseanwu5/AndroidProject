package com.example.myapplication.auth

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.myapplication.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val CARD_BACK_IMAGE = R.drawable.card_back
const val CARD_NUMBERS = 8

@Composable
fun GameScreen(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val images = remember { generateImages(context) }

    // Now properly remembered
    val selectedImages = remember { mutableStateListOf<Boolean>().apply { addAll(List(images.size) { false }) } }
    val isMatched = remember { mutableStateListOf<Boolean>().apply { addAll(List(images.size) { false }) } }

    // Now properly remembered
    var foundPairs by remember { mutableStateOf(0) }
    var lastSelectedIndex by remember { mutableStateOf(-1) }
    var attemptCounts by remember { mutableStateOf(0) }

    // Derived state to determine game completion, doesn't need to be remembered
//    val gameCompleted by derivedStateOf { foundPairs == CARD_NUMBERS }

    // The showCompletionDialog state must be remembered
    val showCompletionDialog = remember { mutableStateOf(false) }

    val lottieComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.view))
    val lottieAnimationState by animateLottieCompositionAsState(
        composition = lottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LottieAnimation(
            composition = lottieComposition,
            progress = lottieAnimationState,
            modifier = Modifier
                .fillMaxSize()
                .scale(1.4f)
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("翻牌次數: $attemptCounts", modifier = Modifier.padding(bottom = 20.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(all = 8.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                items(images.size) { index ->
                    if (!isMatched[index]) {
                        CardView(
                            imageResource = if (selectedImages[index]) images[index].first else CARD_BACK_IMAGE,
                            onClick = {
                                if (!selectedImages[index]) {
                                    selectedImages[index] = true
                                    if (lastSelectedIndex >= 0) {
                                        // Check if a pair is selected
                                        attemptCounts++
                                        if (images[index] == images[lastSelectedIndex]) {
                                            isMatched[index] = true
                                            isMatched[lastSelectedIndex] = true
                                            lastSelectedIndex = -1
                                            foundPairs++
                                            if (foundPairs == CARD_NUMBERS) showCompletionDialog.value = true
                                        } else {
                                            scope.launch {
                                                delay(1000)
                                                selectedImages[index] = false
                                                selectedImages[lastSelectedIndex] = false
                                                lastSelectedIndex = -1
                                            }
                                        }
                                    } else {
                                        lastSelectedIndex = index
                                    }
                                }
                            }
                        )
                    }
                }
            }
            if (showCompletionDialog.value) {
                AlertDialog(
                    onDismissRequest = { showCompletionDialog.value = false },
                    title = { Text("遊戲完成！") },
                    text = { Text("你總共翻了 $attemptCounts 次才完成遊戲。") },
                    confirmButton = {
                        Button(onClick = { showCompletionDialog.value = false }) {
                            Text("太好了！")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun CardView(imageResource: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .size(100.dp)
            .background(MyColors.Yellow)
            .clickable(onClick = onClick)
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            modifier = Modifier.scale(0.8f)
        )
    }
}

fun generateImages(context: Context): List<Pair<Int, Boolean>> {
    val images = mutableListOf<Int>()
    for (i in 1..CARD_NUMBERS) {
        val imageName = "img_$i"
        val imageResourceId = context.resources.getIdentifier(imageName, "drawable", context.packageName)
        images.add(imageResourceId)
        images.add(imageResourceId)
    }
    images.shuffle()
    return images.map { it to false }
}

object MyColors {
    val Yellow = Color(0xFFFFEB3B)
    val White = Color(0xFFFFFFFF)
}