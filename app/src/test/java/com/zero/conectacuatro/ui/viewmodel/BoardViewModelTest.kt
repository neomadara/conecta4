package com.zero.conectacuatro.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zero.conectacuatro.domain.repository.DotRepository
import com.zero.conectacuatro.utils.defaultDots
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BoardViewModelTest {

    @MockK
    private lateinit var dotRepository: DotRepository

    private lateinit var boardViewModel: BoardViewModel

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        boardViewModel = BoardViewModel(dotRepository)
    }

    @After
    fun onAfter() {
        boardViewModel.newGame()
    }

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `when view model is created, get a list of Dots by default` () {
        // given
        val dotsByDefault = defaultDots()
        every { dotRepository.getDots() } returns dotsByDefault
        every { dotRepository.resetDots() } returns Unit
        // when
        boardViewModel.onCreated()
        // then
        assert(boardViewModel.dots.value == dotsByDefault)
    }

    @Test
    fun `when the player touches a dot in a column that has no other dot selected, the last dot in that column must be painted` () {
        // given
        val dots = defaultDots()
        val dotSelected = dots[1]
        val dotsExpected = defaultDots()
        dotsExpected.find { it.row == 5 && it.column == 0 }?.isActive = true
        every { dotRepository.getDots() } returns dots
        every { dotRepository.resetDots() } returns Unit

        // when
        boardViewModel.onCreated()
        every { dotRepository.setDot(dotSelected) } returns Unit
        boardViewModel.selectDot(dotSelected)

        // then
        dots.find { it.row == 5 && it.column == 0 }?.isActive = true
        every { dotRepository.getDots() } returns dotsExpected

        assert(boardViewModel.dots.value == dotsExpected)
    }

    @Test
    fun `when the player touches a dot in a column that has just one painted, must paint the next dot after the last in that column` () {
        // given
        val dots = defaultDots()
        val dotSelected = dots[2]
        val dotsExpected = defaultDots()
        dotsExpected.find { it.row == 0 && it.column == 0 }?.isActive = true
        dotsExpected.find { it.row == 0 && it.column == 1 }?.isActive = true
        every { dotRepository.getDots() } returns dots
        every { dotRepository.resetDots() } returns Unit

        // when
        boardViewModel.onCreated()
        every { dotRepository.setDot(dotSelected) } returns Unit
        boardViewModel.selectDot(dotSelected)

        // then
        dots.find { it.row == 0 && it.column == 0 }?.isActive = true
        dots.find { it.row == 0 && it.column == 1 }?.isActive = true
        every { dotRepository.getDots() } returns dotsExpected

        assert(boardViewModel.dots.value == dotsExpected)
    }
}