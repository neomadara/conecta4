package com.zero.conectacuatro.ui.view

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class BoardTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun boardTest_renderMatrixOf42Dots () {
        composeTestRule.setContent {
            Board()
        }

        composeTestRule.onRoot().printToLog("currentLabelExists")
        composeTestRule
            .onAllNodesWithTag("MyDotTag")
            .assertCountEquals(42)
    }
}