package com.example.myapplication.auth

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.BoardBase
import com.example.myapplication.BoardCellValue
import com.example.myapplication.Circle
import com.example.myapplication.Cross
import com.example.myapplication.GameState
import com.example.myapplication.GameViewModel
import com.example.myapplication.R
import com.example.myapplication.UserAction
import com.example.myapplication.VictoryType
import com.example.myapplication.WinDiagonalLine1
import com.example.myapplication.WinDiagonalLine2
import com.example.myapplication.WinHorizontalLine1
import com.example.myapplication.WinHorizontalLine2
import com.example.myapplication.WinHorizontalLine3
import com.example.myapplication.WinVerticalLine1
import com.example.myapplication.WinVerticalLine2
import com.example.myapplication.WinVerticalLine3
import com.example.myapplication.ui.theme.BlueCustom
import com.example.myapplication.ui.theme.GrayBackground
import com.example.myapplication.ui.theme.GreenishYellow



@Composable
fun BlinkText(text: String, fontSize: TextUnit, fontStyle: FontStyle, color: Color) {
    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Text(
        text = text,
        fontSize = fontSize,
        fontStyle = fontStyle,
        color = color.copy(alpha = alpha)
    )
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun GameScreen1(
    viewModel: GameViewModel,
    navController: NavController
){

    val state = viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBackground)
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Player 'O'
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "玩家 ", fontSize = 16.sp)

                Image(
                    painter = painterResource(id = R.drawable.normaldog),
                    contentDescription = "Player O",
                    modifier = Modifier.size(45.dp)
                )
                Text(text = " : ${state.playerCircleCount}", fontSize = 16.sp)
            }

            // Draw
            Text(text = "平局 : ${state.drawCount}", fontSize = 16.sp)

            // Player 'X'
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "玩家 ", fontSize = 16.sp)
                Image(
                    painter = painterResource(id = R.drawable.firedog),
                    contentDescription = "Player X",
                    modifier = Modifier.size(40.dp)
                )
                Text(text = " : ${state.playerCrossCount}", fontSize = 16.sp)
            }
        }
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Text(text = "Player 'O' : ${state.playerCircleCount}", fontSize = 16.sp)
//            Text(text = "Draw : ${state.drawCount}", fontSize = 16.sp)
//            Text(text = "Player 'X' : ${state.playerCrossCount}", fontSize = 16.sp)
//        }
        Text(
            text = "yo! battle!",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            color = BlueCustom
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(20.dp)
                )
                .clip(RoundedCornerShape(20.dp))
                .background(GrayBackground),
            contentAlignment = Alignment.Center
        ) {
            BoardBase()
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .aspectRatio(1f),
                columns = GridCells.Fixed(3)
            ) {
                viewModel.boardItems.forEach { (cellNo, boardCellValue) ->
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clickable(
                                    interactionSource = MutableInteractionSource(),
                                    indication = null
                                ) {
                                    viewModel.onAction(
                                        UserAction.BoardTapped(cellNo)
                                    )
                                },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            AnimatedVisibility(
                                visible = viewModel.boardItems[cellNo] != BoardCellValue.NONE,
                                enter = scaleIn(tween(1000))
                            ) {
                                if (boardCellValue == BoardCellValue.CIRLCE) {
                                    Circle()
                                } else if (boardCellValue == BoardCellValue.CROSS) {
                                    Cross()
                                }
                            }
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AnimatedVisibility(
                    visible = state.hasWon,
                    enter = fadeIn(tween(2000))
                ) {
                    DrawVictoryLine(state = state)
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 使用BlinkText代替原本的Text，应用于显示game state hints
            BlinkText(
                text = state.hintText,
                fontSize = 24.sp,
                fontStyle = FontStyle.Normal,
                color = GreenishYellow // 或者根据您的UI需求选择合适的颜色
            )
            Button(
                onClick = {
                    viewModel.onAction(UserAction.PlayAgainButtonClicked)
                },
                shape = RoundedCornerShape(5.dp),
//                elevation = ButtonDefaults.elevation(5.dp),
                colors = ButtonDefaults.buttonColors(containerColor = BlueCustom),
            ) {
                Text(
                    text = "但是你拒絕",
                    fontSize = 16.sp
                )
            }

        }
    }
}

@Composable
fun DrawVictoryLine(
    state: GameState
)  {
    when (state.victoryType) {
        VictoryType.HORIZONTAL1 -> WinHorizontalLine1()
        VictoryType.HORIZONTAL2 -> WinHorizontalLine2()
        VictoryType.HORIZONTAL3 -> WinHorizontalLine3()
        VictoryType.VERTICAL1 -> WinVerticalLine1()
        VictoryType.VERTICAL2 -> WinVerticalLine2()
        VictoryType.VERTICAL3 -> WinVerticalLine3()
        VictoryType.DIAGONAL1 -> WinDiagonalLine1()
        VictoryType.DIAGONAL2 -> WinDiagonalLine2()
        VictoryType.NONE -> {}
    }
}


@Preview
@Composable
fun GameScreenPreview() {
    val navController = rememberNavController()
    val viewModel = GameViewModel()
    GameScreen1(viewModel = viewModel, navController = navController)
}