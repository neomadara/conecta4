package com.zero.conectacuatro.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zero.conectacuatro.domain.model.Dot
import androidx.lifecycle.MutableLiveData
import com.zero.conectacuatro.data.data_source.DotProvider
import com.zero.conectacuatro.data.repository.DotRepositoryImpl
import com.zero.conectacuatro.domain.repository.DotRepository

class BoardViewModel (
    private val repository: DotRepository = DotRepositoryImpl(DotProvider())
) : ViewModel() {
    private val _dots = MutableLiveData<List<Dot>>()
    val dots: LiveData<List<Dot>> get() = _dots

    fun selectDot(dot: Dot) {
        repository.setDot(dot)
        fillDots()
    }

    fun onCreated() {
        fillDots()
    }

    private fun fillDots() {
        _dots.postValue(repository.getDots())
    }

    fun newGame() {
        repository.resetDots()
    }
}