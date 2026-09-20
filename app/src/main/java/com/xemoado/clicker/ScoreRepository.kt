package com.xemoado.clicker

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

private val Context.dataStore by preferencesDataStore(name = "clicker_prefs")

interface ScoreStorage {
    val scoreFlow: Flow<Long>
    suspend fun saveScore(score: Long)
}

class ScoreRepository(private val context: Context) : ScoreStorage {

    private val scoreKey = longPreferencesKey("score")

    override val scoreFlow: Flow<Long> = context.dataStore.data
        .catch { e ->
            if (e is IOException) emit(emptyPreferences()) else throw e
        }
        .map { prefs -> prefs[scoreKey] ?: 0L }

    override suspend fun saveScore(score: Long) {
        context.dataStore.edit { prefs ->
            prefs[scoreKey] = score
        }
    }
}
