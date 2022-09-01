package com.zero.conectacuatro.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.zero.conectacuatro.domain.model.Dot
import com.zero.conectacuatro.data.data_source.DotProvider
import com.zero.conectacuatro.data.repository.DotRepositoryImpl
import com.zero.conectacuatro.domain.model.GameStatus
import com.zero.conectacuatro.domain.repository.DotRepository

class BoardViewModel(
    private val repository: DotRepository = DotRepositoryImpl(DotProvider())
) : ViewModel() {

    private val _dots: MutableState<List<Dot>> = mutableStateOf(emptyList())
    val dots: State<List<Dot>> get() = _dots

    private val _player: MutableState<Number> = mutableStateOf(2)
    val player: State<Number> get() = _player

    private val _gameStatus: MutableState<GameStatus> = mutableStateOf(GameStatus(0,0))
    val gameStatus: State<GameStatus> get() = _gameStatus

    fun selectDot(dot: Dot) {
        if (dot.isActive) return
        when (_player.value) {
            2 -> dot.playerId = 1
            1 -> dot.playerId = 2
        }
        _player.value = dot.playerId
        repository.setDot(dot)
        fillDots()
        searchWinner(_player.value)
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
        fillDots()
    }

    fun searchWinner(playerNumber: Number) {
      val result = repository.searchWinner(playerNumber)
      if (result.dotsInLine == 4) {
        when(playerNumber) {
            1 -> _gameStatus.value.winsPlayerOne = _gameStatus.value.winsPlayerOne.toInt() + 1
            2 -> _gameStatus.value.winsPlayerTwo = _gameStatus.value.winsPlayerTwo.toInt() + 1
        }
        newGame()
      }
    }
}