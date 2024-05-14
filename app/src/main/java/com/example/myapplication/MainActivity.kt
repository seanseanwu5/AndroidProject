package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.auth.LoginScreen
import com.example.myapplication.auth.MainScreen
import com.example.myapplication.auth.MemeScreen
import com.example.myapplication.auth.SignupScreen
import com.example.myapplication.auth.SuccessScreen
import com.example.myapplication.main.NotificationMessage
import com.example.myapplication.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            window.statusBarColor = getColor(R.color.black)
            window.navigationBarColor = getColor(R.color.black)
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AuthenticationApp(this.onBackPressedDispatcher)
                }
            }
        }
    }
}


sealed class DestinationScreen(val route: String) {
    object Main: DestinationScreen("main")
    object Signup: DestinationScreen("signup")
    object Login: DestinationScreen("login")
    object Success: DestinationScreen("success")
}

@Composable
fun AuthenticationApp(onBackPressedDispatcher: OnBackPressedDispatcher) {
    val vm = hiltViewModel<FbViewModel>() // Corrected ViewModel class
    val navController = rememberNavController()

    NotificationMessage(vm)

    NavHost(
        navController = navController,
        startDestination = DestinationScreen.Main.route
    ) {
        composable(DestinationScreen.Main.route) {
            MainScreen(navController, vm)
        }
        composable(DestinationScreen.Signup.route) {
            SignupScreen(navController, vm)
        }
        composable(DestinationScreen.Login.route) {
            LoginScreen(navController, vm)
        }
        composable(DestinationScreen.Success.route) {
            SuccessScreen(navController, vm)
        }
        composable("piano") {
            com.example.myapplication.auth.ClockScreen()
        }
        composable("bus") {
            com.example.myapplication.auth.BusScreen()
        }
        composable("meme") {
            MemeScreen(navController)
        }
    }
}
