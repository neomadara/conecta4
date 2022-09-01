package com.zero.conectacuatro.ui.view

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.zero.conectacuatro.domain.model.Dot
import com.zero.conectacuatro.domain.model.GameStatus
import org.junit.Rule
import org.junit.Test

class BoardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun defaultDots(): MutableList<Dot> {
        val dots = mutableListOf<Dot>()

        repeat(7) { col ->
            repeat(6) { row ->
                dots.add(Dot(false, col, row, 0))
            }
        }

        return dots
    }

    @Test
    fun boardTest_renderMatrixOf42Dots () {
        val dots = defaultDots()
        composeTestRule.setContent {
            Board(dots = dots.toList(), {})
        }

        composeTestRule.onRoot().printToLog("currentLabelExists")
        composeTestRule
            .onAllNodesWithTag("MyDotTag")
            .assertCountEquals(42)
    }

    @Test
    fun boardTest_renderAMarkerForTheGameStatus () {
        val dots = defaultDots()
        composeTestRule.setContent {
            Board(dots = dots.toList(), {})
            GamerMarkerNew(actualPlayer = 1, GameStatus(1, 0), {})
        }

        composeTestRule.onRoot().printToLog("currentLabelExists")
        composeTestRule
            .onAllNodesWithTag("GameStatusTagPlayerOne")
            .assertCountEquals(1)

        composeTestRule
            .onAllNodesWithTag("GameStatusTagPlayerTwo")
            .assertCountEquals(1)
    }
}