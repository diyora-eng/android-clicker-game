package com.xemoado.clicker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ClickerViewModel(
    private val repository: ScoreStorage
) : ViewModel() {

    private val model = GameModel()
    private val _uiState = MutableStateFlow(ClickerUiState())
    val uiState: StateFlow<ClickerUiState> = _uiState.asStateFlow()

    private var saveJob: Job? = null

    init {
        viewModelScope.launch {
            val savedScore = repository.scoreFlow.first()
            model.restore(savedScore)
            syncState()
        }
    }

    fun onClick() {
        model.click()
        syncState()

        saveJob?.cancel()
        saveJob = viewModelScope.launch {
            delay(500)
            repository.saveScore(model.score)
        }
    }

    private fun syncState() {
        _uiState.value = ClickerUiState(
            score = model.score,
            isLoading = false
        )
    }
}