package com.xemoado.clicker
import org.junit.Assert.assertEquals
import org.junit.Test

class GameModelTest {

    @Test
    fun newGameStartsWithZero() {
        val model = GameModel()
        assertEquals(0L, model.score)
    }

    @Test
    fun clickIncreasesScoreByOne() {
        val model = GameModel()
        model.click()
        assertEquals(1L, model.score)
    }

    @Test
    fun multipleClicksAccumulate() {
        val model = GameModel()
        repeat(10) { model.click() }
        assertEquals(10L, model.score)
    }

    @Test
    fun restoreSetsSavedValue() {
        val model = GameModel()
        model.restore(42L)
        assertEquals(42L, model.score)
    }

    @Test
    fun clickAfterRestoreContinuesFromSavedValue() {
        val model = GameModel()
        model.restore(100L)
        model.click()
        assertEquals(101L, model.score)
    }
}
