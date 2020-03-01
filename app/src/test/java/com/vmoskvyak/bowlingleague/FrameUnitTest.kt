package com.vmoskvyak.bowlingleague

import com.vmoskvyak.bowlingleague.logic.Frame
import org.junit.Assert
import org.junit.Test

class FrameUnitTest {

    @Test
    fun addThrow_FrameIsNotFinished() {
        val frame = Frame()
        frame.addThrow(4)
        Assert.assertFalse("Frame is not finished", frame.isFrameFinished)
    }

    @Test
    fun addThrow_FrameIsFinished() {
        val frame = Frame()
        frame.addThrow(4)
        frame.addThrow(5)
        Assert.assertTrue("Frame is finished", frame.isFrameFinished)
    }

    @Test
    fun addThrow_StrikeFrameIsNotFinished() {
        val frame = Frame()
        frame.addThrow(10)
        Assert.assertFalse("Frame is not finished", frame.isFrameFinished)
    }

    @Test
    fun addThrow_StrokeFrameIsNotFinished() {
        val frame = Frame()
        frame.addThrow(6)
        frame.addThrow(4)
        Assert.assertFalse("Frame is not finished", frame.isFrameFinished)
    }

    @Test
    fun addThrow_ThrowIsNotFinished() {
        val frame = Frame()
        frame.addThrow(6)
        Assert.assertFalse("Throw is not finished", frame.isThrowFinished)
    }


    @Test
    fun addThrow_ThrowIsFinished() {
        val frame = Frame()
        frame.addThrow(6)
        frame.addThrow(1)
        Assert.assertTrue("Throw is finished", frame.isThrowFinished)
    }

    @Test
    fun addThrow_FirstThrowIsFinishedWithStrike() {
        val frame = Frame()
        frame.addThrow(10)
        Assert.assertTrue("Throw is finished", frame.isThrowFinished)
    }

    @Test
    fun addThrow_ThrowIsFinishedWithoutStrikeAndWithoutStroke() {
        val frame = Frame()
        frame.addThrow(2)
        frame.addThrow(2)
        Assert.assertTrue("Throw is finished", frame.isThrowFinished)
    }
}