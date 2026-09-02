package com.xemoado.clicker
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen(onGoBack: ()->Unit){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment =Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )

    {
        Text(text="Настройки",fontSize=32.sp)
        Spacer(modifier=Modifier.height(20.dp))
        Text(text="Разрабатывается",fontSize=16.sp)
        Spacer(modifier=Modifier.height(40.dp))
        Button(onClick=onGoBack) {
            Text(text="Назад")
        }
    }
}


