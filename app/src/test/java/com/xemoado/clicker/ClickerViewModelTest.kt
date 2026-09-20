package com.xemoado.clicker

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ClickerViewModelTest {


    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun loadsSavedScoreOnStart() = runTest(testDispatcher) {

        val fake = FakeScoreRepository(initialScore = 42L)

        val viewModel = ClickerViewModel(fake)


        advanceUntilIdle()

        assertEquals(42L, viewModel.uiState.value.score)
        assertFalse(viewModel.uiState.value.isLoading)
    }

    @Test
    fun clickIncreasesScoreImmediately() = runTest(testDispatcher) {
        val viewModel = ClickerViewModel(FakeScoreRepository())
        advanceUntilIdle()

        viewModel.onClick()


        assertEquals(1L, viewModel.uiState.value.score)
    }

    @Test
    fun debounceSavesOnlyOnceForManyClicks() = runTest(testDispatcher) {
        val fake = FakeScoreRepository()
        val viewModel = ClickerViewModel(fake)
        advanceUntilIdle()


        repeat(20) {
            viewModel.onClick()
            advanceTimeBy(10)
        }


        advanceUntilIdle()


        assertEquals(1, fake.saveCallCount)
        assertEquals(20L, fake.lastSavedScore)
    }

    @Test
    fun noSaveBeforeDebounceDelay() = runTest(testDispatcher) {
        val fake = FakeScoreRepository()
        val viewModel = ClickerViewModel(fake)
        advanceUntilIdle()

        viewModel.onClick()


        advanceTimeBy(100)

        assertEquals(0, fake.saveCallCount)
    }
}
