package com.example.myapplication.auth

import android.content.Context
import android.media.MediaPlayer
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
import com.example.myapplication.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// 使用卡片背面的图片资源ID
val CARD_BACK_IMAGE = R.drawable.card_back

@Composable
fun GameScreen(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // 生成卡片图片资源ID，并对它们进行混洗
    val images = remember { generateImages(context) }

    // 跟踪每张卡的翻面状态
    val selectedImages = remember { mutableStateListOf<Boolean>().apply { addAll(List(images.size) { false }) } }

    // 跟踪是否有卡片被选中，以及它们是否匹配
    val isMatched = remember { mutableStateListOf<Boolean>().apply { addAll(List(images.size) { false }) } }

    // 跟踪找到的对数
    var foundPairs by remember { mutableStateOf(0) }

    // 最后一次选中的卡片索引
    var lastSelectedIndex by remember { mutableStateOf(-1) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            // 可以根据需要添加标题或指示器
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                contentPadding = PaddingValues(all = 8.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                items(images.size) { index ->
                    if (!isMatched[index]) {  // 不显示已被匹配的卡片
                        CardView(
                            imageResource = if (selectedImages[index]) images[index].first else CARD_BACK_IMAGE,
                            onClick = {
                                if (!selectedImages[index]) {
                                    selectedImages[index] = true
                                    if (lastSelectedIndex >= 0) {
                                        if (images[index] == images[lastSelectedIndex]) {
                                            isMatched[index] = true
                                            isMatched[lastSelectedIndex] = true
                                            lastSelectedIndex = -1
                                            foundPairs++
                                        } else {
                                            scope.launch {
                                                delay(1000) // 等待1秒
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
        images.add(imageResourceId) // 由于卡片是成对的，所以每个图片资源添加两次
        images.add(imageResourceId)
    }
    images.shuffle()
    return images.map { it to false }
}

object SoundUtil {
    var mediaPlayer: MediaPlayer? = null

    fun playSound(context: Context, resourceId: Int) {
        release() // 释放之前的 mediaPlayer 资源
        mediaPlayer = MediaPlayer.create(context, resourceId)
        mediaPlayer?.start()
        mediaPlayer?.setOnCompletionListener {
            release()
        }
    }

    fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}

object MyColors {
    val Yellow = Color(0xFFFFEB3B)
    val White = Color(0xFFFFFFFF)
}

const val CARD_NUMBERS = 8






//package com.example.myapplication.auth
//
//
//import android.content.Context
//import android.media.MediaPlayer
//import android.os.Bundle
//import android.text.Layout.Alignment
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.contentColorFor
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableIntStateOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
//import java.lang.reflect.Modifier
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        installSplashScreen()
//        setContent {
//            MyMemoryTheme {
//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ){
//                    MemoryGame()
//                }
//            }
//        }
//    }
//}
//
//@composable
//private fun MemoryGame(){
//    val context = LocalContext.current
//    var images = remember { generateImages(context) }
//    var selectedImages by remember { mutableStateOf(images.map { -1 to false })}
//    var foundParis by remember { mutableIntStateOf(0) }
//    var lastSelectedImage by remember { mutableIntStateOf(-1) }
//    var imageNumber by remember { mutableIntStateOf(1) }
//    var lastSelectedIndex by remember { mutableIntStateOf(-1) }
//    var canPlay by remember { mutableIntStateOf(true) }
//    val scope = rememberCoroutineScope()
//    var elapsedTime by remember { mutableIntStateOf(0L) }
//    var showTime by remember { mutableIntStateOf(true) }
//    var timeText by remember { mutableIntStateOf("00:00: sg") }
//}
//
//
//fungenerateImage(context: Context) :List<Pair<Int, Boolean>>{
//    val images = mutableListOf<<Int>()
//    val numberOfPairs = 8
//    for (i in 1 ..numberOfPairs){
//        val imageName = "img_$i"
//        val imageResourceId = content.resource.getIdentifier(imageName, "drawable", context.packageName)
//        images.add(imageResourceId)
//        images.add(imageResourceId)
//    }
//    images.shuffle()
//    return images.map { it to false }
//}
//
//object SoundUtil {
//    val mediaPlayer:MediaPlayer? = null
//    fun playSound(context: Context, resourceId: Int) {
//        mediaPlayer?. release()
//        mediaPlayer = MediaPlayer.create(context, resourceId)
//        mediaPlayer?.start()
//        mediaPlayer?.setOnCompletionListener {
//            relese()
//        }
//    }
//    fun release(){
//        mediaPlayer?. release()
//        mediaPlayer = null
//    }
//}
//object  MyColors{
//    val Yellow = Color( color: 0xFFFFEB3B)
//    val White = Color(color: 0xFFFFFFFF)
//}