package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
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
import com.example.myapplication.auth.ClockScreen
import com.example.myapplication.auth.LoginScreen
import com.example.myapplication.auth.MainScreen
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
                    AuthenticationApp()
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
fun AuthenticationApp() {
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
        composable("clock") {
            ClockScreen()
        }
    }
}
//下面是好像有問題YT的
//@Composable
//fun AuthenticationApp() {
//    val vm = hiltViewModel<IgViewModel>()
//    val navController = rememberNavController()
//
//    NotificationMessage(vm)
//
//    NavHost(
//        navController = navController,
//        startDestination = DestinationScreen.Main.route
//    ) {
//        composable(DestinationScreen.Main.route) {
//            MainScreen(navController, vm)
//        }
//        composable(DestinationScreen.Signup.route) {
//            SignupScreen(navController, vm)
//        }
//        composable(DestinationScreen.Login.route) {
//            LoginScreen(navController, vm)
//        }
//        composable(DestinationScreen.Success.route) {
//            SuccessScreen(navController, vm)
//        }
//    }
//}



//package com.example.myapplication
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.example.myapplication.auth.LoginScreen
//import com.example.myapplication.auth.MainScreen
//import com.example.myapplication.auth.SignupScreen
//import com.example.myapplication.auth.SuccessScreen
//import com.example.myapplication.main.NotificationMessage
//import com.example.myapplication.ui.theme.MyApplicationTheme
//import dagger.hilt.android.AndroidEntryPoint
//
//@AndroidEntryPoint
//class MainActivity: ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            window.statusBarColor = getColor(R.color.black)
//            window.navigationBarColor = getColor(R.color.black)
//            MyApplicationTheme { // Ensure you have a theme called MyApplicationTheme
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    AuthenticationApp()
//                }
//            }
//        }
//    }
//}
//
//sealed class DestinationScreen(val route: String) {
//    object Main : DestinationScreen("main")
//    object Signup : DestinationScreen("signup")
//    object Login : DestinationScreen("login") // Adjusted "Login" to "login"
//    object Success : DestinationScreen("success")
//}
//
//@Composable
//fun AuthenticationApp() {
//    val vm: FbViewModel = hiltViewModel() // Typing and missing colon added
//    val navController = rememberNavController()
//
//    NotificationMessage(vm)
//
//    NavHost(navController = navController, startDestination = DestinationScreen.Main.route) {
//        composable(route = DestinationScreen.Main.route) {
//            MainScreen(navController = navController, vm = vm)
//        }
//        composable(route = DestinationScreen.Signup.route) {
//            SignupScreen(navController = navController, vm = vm)
//        }
//        composable(route = DestinationScreen.Login.route) {
//            LoginScreen(navController = navController, vm = vm)
//        }
//        composable(route = DestinationScreen.Success.route) {
//            SuccessScreen(navController = navController, vm = vm)
//        }
//    }
//}