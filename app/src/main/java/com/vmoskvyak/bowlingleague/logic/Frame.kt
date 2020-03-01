package com.vmoskvyak.bowlingleague.logic

class Frame {
    private var firstThrowResult = NOT_INITIALIZED_POINT
    private var secondThrowResult = NOT_INITIALIZED_POINT

    val result: Int
        get() {
            val checkedFirstThrowResult =
                if (firstThrowResult == NOT_INITIALIZED_POINT) 0 else firstThrowResult
            val checkedSecondThrowResult =
                if (secondThrowResult == NOT_INITIALIZED_POINT) 0 else secondThrowResult

            return checkedFirstThrowResult + checkedSecondThrowResult
        }

    val isFrameFinished: Boolean
        get() {
            if (firstThrowResult == NOT_INITIALIZED_POINT || secondThrowResult == NOT_INITIALIZED_POINT) {
                return false
            }

            if (result == MAX_POINTS) { // Spare
                return false
            }

            return firstThrowResult != MAX_POINTS && secondThrowResult != MAX_POINTS
        }

    val isThrowFinished: Boolean
        get() {
            if (firstThrowResult == MAX_POINTS) return true
            return firstThrowResult != NOT_INITIALIZED_POINT && secondThrowResult != NOT_INITIALIZED_POINT
        }

    fun addThrow(points: Int) {
        if (firstThrowResult == NOT_INITIALIZED_POINT) {
            firstThrowResult = points
        } else if (secondThrowResult == NOT_INITIALIZED_POINT) {
            secondThrowResult = points
        }
    }

    companion object {
        private const val NOT_INITIALIZED_POINT = -1
        private const val MAX_POINTS = 10
    }
}
