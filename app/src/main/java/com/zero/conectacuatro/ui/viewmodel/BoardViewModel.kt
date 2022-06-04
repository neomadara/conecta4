package com.zero.conectacuatro.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.zero.conectacuatro.domain.model.Dot

data class BoardUiState(
    val dots: List<Dot> = emptyList()
)

class BoardViewModel () : ViewModel() {



}