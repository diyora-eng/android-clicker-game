package com.xemoado.clicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
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
enum class Screen{
    Main,Settings
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           AppNavigation()
        }
    }
}
@Composable
fun AppNavigation(viewModel: ClickerViewModel = viewModel()) {
    var currentScreen by remember { mutableStateOf(Screen.Main) }

    BackHandler(enabled = currentScreen == Screen.Settings) {
        currentScreen = Screen.Main
    }

    when (currentScreen) {
        Screen.Main -> ClickerScreen(
            viewModel = viewModel,
            onOpenSettings = { currentScreen = Screen.Settings }
        )
        Screen.Settings -> SettingsScreen(
            onGoBack = { currentScreen = Screen.Main }
        )
    }
}



@Composable
fun ClickerScreen(
    viewModel: ClickerViewModel = viewModel(),
    onOpenSettings: () -> Unit
) {
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

        Spacer(modifier = Modifier.height(60.dp))

        Button(onClick = onOpenSettings) {
            Text(text = "⚙ Настройки")
        }
    }
}

