package com.vmoskvyak.bowlingleague.logic

import com.vmoskvyak.bowlingleague.model.Player

class GameFlow(private var players: List<Player>) {

    private var indexOfCurrentPlayer = 0
    private var playerFlow = PlayerFlow(players[0])

    fun addThrowResult(result: Int) {
        if (playerFlow.currentFrame == null) {
            playerFlow.addFrame()
        }

        val frames = playerFlow.frames
        if (frames.isNotEmpty()) {
            calculateStrikeOrStrokeIfNecessary(frames)
        }

        playerFlow.currentFrame?.let { frame ->
            if (!frame.isThrowFinished) {
                frame.addThrow(result)
            } else {
                moveToNexPlayer()
            }
        }
    }

    fun getCurrentPlayerScore(): Int {
        return playerFlow.currentFrame?.result ?: 0
    }

    private fun calculateStrikeOrStrokeIfNecessary(frames: ArrayList<Frame>) {
        val notFinishedFrame = frames.dropLast(1).firstOrNull { frame -> !frame.isFrameFinished } ?: return

        //TODO: calculate strike or stroke
    }

    private fun moveToNexPlayer() {
        if (++indexOfCurrentPlayer >= players.size) {
            //TODO: Move to first player and switch to second frame. If all frames done Game is finished
            return
        }

        playerFlow.setCurrentPlayer(players[indexOfCurrentPlayer])
    }

}