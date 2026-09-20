package com.xemoado.clicker

class GameModel {

    var score: Long = 0
        private set

    fun click() {
        score += 1
    }
    fun restore(saved: Long) {
        score = saved
    }
}
