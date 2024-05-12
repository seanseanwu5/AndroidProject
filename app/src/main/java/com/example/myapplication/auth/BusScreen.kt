package com.example.myapplication.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusScreen() {
    // 初始化公車時刻表
    val busSchedule = remember { mutableStateOf(initBusSchedule()) }
    var busRoute by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var schedule by remember { mutableStateOf(listOf<String>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = busRoute,
            onValueChange = { busRoute = it },
            label = { Text("Bus Route") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = time,
            onValueChange = { time = it },
            label = { Text("Time") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                schedule = getBusSchedule(busRoute, time, busSchedule.value)
            })
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            schedule = getBusSchedule(busRoute, time, busSchedule.value)
        }) {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 显示时刻表
        schedule.forEach {
            Text(it)
        }
    }
}

// 初始化公車时刻表函数
fun initBusSchedule(): Map<String, List<String>> {
    val busSchedule = mutableMapOf<String, List<String>>()

    busSchedule["Route 1"] = listOf("08:00", "10:00", "12:00", "14:00")
    busSchedule["Route 2"] = listOf("09:00", "11:00", "13:00", "15:00")
    // ... 添加更多路线

    return busSchedule
}

// 根据公車路線和时间获取时刻表函数
fun getBusSchedule(busRoute: String, time: String, schedule: Map<String, List<String>>): List<String> {
    val scheduleList: MutableList<String> = mutableListOf()
    schedule[busRoute]?.filter { it >= time }?.let {
        scheduleList.addAll(it)
    }
    return scheduleList
}
//
//import android.R
//import android.os.Bundle
//import android.widget.ArrayAdapter
//import android.widget.Button
//import android.widget.EditText
//import android.widget.ListView
//import androidx.appcompat.app.AppCompatActivity
//
//
//class MainActivity : AppCompatActivity() {
//    private var busSchedule: MutableMap<String, List<String>>? = null
//    protected fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        // 初始化公車時刻表
//        initBusSchedule()
//        val busRouteInput: EditText = findViewById(R.id.busRouteInput)
//        val timeInput: EditText = findViewById(R.id.timeInput)
//        val searchButton: Button = findViewById(R.id.searchButton)
//        val scheduleListView: ListView = findViewById(R.id.scheduleListView)
//        searchButton.setOnClickListener {
//            val busRoute = busRouteInput.text.toString()
//            val time = timeInput.text.toString()
//            val schedule = getBusSchedule(busRoute, time)
//            val adapter: ArrayAdapter<String> = ArrayAdapter<Any?>(
//                this@MainActivity,
//                R.layout.simple_list_item_1, schedule
//            )
//            scheduleListView.adapter = adapter
//        }
//    }
//
//    // 初始化公車時刻表
//    private fun initBusSchedule() {
//        busSchedule = HashMap()
//        val route1Schedule: MutableList<String> = ArrayList()
//        route1Schedule.add("08:00")
//        route1Schedule.add("10:00")
//        route1Schedule.add("12:00")
//        route1Schedule.add("14:00")
//        busSchedule["Route 1"] = route1Schedule
//        val route2Schedule: MutableList<String> = ArrayList()
//        route2Schedule.add("09:00")
//        route2Schedule.add("11:00")
//        route2Schedule.add("13:00")
//        route2Schedule.add("15:00")
//        busSchedule["Route 2"] = route2Schedule
//
//        // 添加更多公車路線的時刻表
//    }
//
//    // 根據公車路線和時間獲取時刻表
//    private fun getBusSchedule(busRoute: String, time: String): List<String> {
//        val schedule: MutableList<String> = ArrayList()
//        if (busSchedule!!.containsKey(busRoute)) {
//            for (scheduleTime in busSchedule!![busRoute]!!) {
//                if (scheduleTime.compareTo(time) >= 0) {
//                    schedule.add(scheduleTime)
//                }
//            }
//        }
//        return schedule
//    }
//}