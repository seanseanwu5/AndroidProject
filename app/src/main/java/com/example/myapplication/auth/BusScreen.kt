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

        schedule.forEach {
            Text(it)
        }
    }
}

fun initBusSchedule(): Map<String, List<String>> {
    val busSchedule = mutableMapOf<String, List<String>>()

    busSchedule["Route 1"] = listOf("08:00", "10:00", "12:00", "14:00")
    busSchedule["Route 2"] = listOf("09:00", "11:00", "13:00", "15:00")

    return busSchedule
}

fun getBusSchedule(busRoute: String, time: String, schedule: Map<String, List<String>>): List<String> {
    val scheduleList: MutableList<String> = mutableListOf()
    schedule[busRoute]?.filter { it >= time }?.let {
        scheduleList.addAll(it)
    }
    return scheduleList
}
