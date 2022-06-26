package com.zero.conectacuatro.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zero.conectacuatro.domain.repository.DotRepository
import com.zero.conectacuatro.utils.defaultDots
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BoardViewModelTest {

    @RelaxedMockK
    private lateinit var dotRepository: DotRepository

    private lateinit var boardViewModel: BoardViewModel

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        boardViewModel = BoardViewModel()
    }

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `when viewModel is created the first time, get a list of Dots by default` () {
        // given
        val dotsByDefault = defaultDots()
        every { dotRepository.getDots() } returns dotsByDefault
        // when
        boardViewModel.onCreated()
        // then
        assert(boardViewModel.dots.value == dotsByDefault)
    }
}