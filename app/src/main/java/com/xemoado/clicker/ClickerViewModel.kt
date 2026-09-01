package com.xemoado.clicker
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ClickerViewModel:ViewModel() {
    private val model=GameModel()
    private val sco = MutableStateFlow(model.score)
    val score: StateFlow<Int> = sco

    fun incrementScore(){
        model.score++
        sco.value=model.score

    }
}
