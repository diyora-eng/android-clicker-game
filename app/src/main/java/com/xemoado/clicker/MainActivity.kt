package com.xemoado.clicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClickerScreen()
        }
    }
}

@Composable
fun ClickerScreen(viewModel: ClickerViewModel = viewModel()) {
    val currentScore by viewModel.score.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Счет: $currentScore", fontSize = 40.sp)

        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = { viewModel.incrementScore() }) {
            Text(text = "КЛИКНИ МЕНЯ!", fontSize = 24.sp)
        }
    }
}