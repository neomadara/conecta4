package com.zero.conectacuatro.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.zero.conectacuatro.domain.model.Dot
import com.zero.conectacuatro.data.data_source.DotProvider
import com.zero.conectacuatro.data.repository.DotRepositoryImpl
import com.zero.conectacuatro.domain.repository.DotRepository

data class BoardUiState(
    val dots: List<Dot> = emptyList(),
    val player: Number = 0
)

class BoardViewModel(
    private val repository: DotRepository = DotRepositoryImpl(DotProvider())
) : ViewModel() {

    private val _state = mutableStateOf(BoardUiState())
    val state: State<BoardUiState> = _state

    private val _dots: MutableState<List<Dot>> = mutableStateOf(emptyList())
    val dots: State<List<Dot>> get() = _dots

    private val _player: MutableState<Number> = mutableStateOf(0)
    val player: State<Number> get() = _player

    fun selectDot(dot: Dot) {
        when (_player.value) {
            0, 2 -> dot.playerId = 1
            1 -> dot.playerId = 2
        }
        _player.value = dot.playerId
        repository.setDot(dot)
        fillDots()
    }

    fun onCreated() {
        fillDots()
    }

    private fun fillDots() {
        val dots = repository.getDots()
        _dots.value = dots
    }

    fun newGame() {
        repository.resetDots()
    }
}