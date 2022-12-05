package com.carqueue.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import com.carqueue.ui.theme.CarQueueTheme
import androidx.compose.material3.*
import com.carqueue.ui.components.DriverView
import com.carqueue.ui.components.DriverViewModel
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val viewModel: DriverViewModel by viewModels()
        setContent {
            CarQueueTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DriverView()
                }
            }
        }
    }
}



