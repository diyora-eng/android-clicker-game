package com.xemoado.clicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import  androidx.compose.material3.CircularProgressIndicator
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory


enum class Screen {
    Main, Settings
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = ScoreRepository(applicationContext)


        setContent {

            AppNavigation(repository)
        }
    }

    @Composable
    fun AppNavigation(repository: ScoreStorage) {


        val viewModel: ClickerViewModel = viewModel(
            factory = viewModelFactory {
                initializer { ClickerViewModel(repository) }
            }
        )


        @Composable
        fun ClickerScreen(
            state: ClickerUiState,
            onClick: () -> Unit,
            onOpenSettings: () -> Unit
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text(text = "Счет: ${state.score}", fontSize = 40.sp)
                }

                Spacer(modifier = Modifier.height(30.dp))


                Button(
                    onClick = onClick,
                    enabled = !state.isLoading
                ) {
                    Text(text = "КЛИКНИ МЕНЯ!", fontSize = 24.sp)
                }

                Spacer(modifier = Modifier.height(60.dp))

                Button(onClick = onOpenSettings) {
                    Text(text = "⚙ Настройки")
                }
            }
        }

        @Preview(showBackground = true)
        @Composable
        fun ClickerScreenPreview() {
            ClickerScreen(
                state = ClickerUiState(score = 999, isLoading = false),
                onClick = {},
                onOpenSettings = {}
            )
        }
    }
}