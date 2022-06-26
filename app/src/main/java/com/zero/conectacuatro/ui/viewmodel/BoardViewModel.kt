package com.zero.conectacuatro.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.zero.conectacuatro.domain.model.Dot
import androidx.lifecycle.MutableLiveData
import com.zero.conectacuatro.data.data_source.DotProvider
import com.zero.conectacuatro.data.repository.DotRepositoryImpl

class BoardViewModel () : ViewModel() {
    private val dotProvider = DotProvider()
    private val dotRepository = DotRepositoryImpl(dotProvider)

    private val _dots = MutableLiveData<List<Dot>>()
    val dots: LiveData<List<Dot>> get() = _dots

    fun selectDot(dot: Dot) {
        println(dot)
    }

    fun onCreated() {
        _dots.postValue(dotRepository.getDots())
    }
}