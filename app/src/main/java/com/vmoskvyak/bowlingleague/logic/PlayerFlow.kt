package com.vmoskvyak.bowlingleague.logic

import com.vmoskvyak.bowlingleague.model.Player

class PlayerFlow(var player: Player) {

    val frames = ArrayList<Frame>(FRAMES_COUNT)
    var currentFrame: Frame? = null

    fun addFrame() {
        val frame = Frame()

        currentFrame = frame
        frames.add(frame)
    }

    fun setCurrentPlayer(player: Player) {
        this.player = player
    }

    companion object {
        const val FRAMES_COUNT = 10
    }
}