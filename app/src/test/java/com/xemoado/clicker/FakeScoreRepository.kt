package com.xemoado.clicker

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeScoreRepository(initialScore: Long = 0L) : ScoreStorage {

    private val _flow = MutableStateFlow(initialScore)

    override val scoreFlow: Flow<Long> = _flow

    var saveCallCount = 0
        private set


    var lastSavedScore: Long? = null
        private set

    override suspend fun saveScore(score: Long) {
        saveCallCount++
        lastSavedScore = score
                _flow.value = score
    }
}