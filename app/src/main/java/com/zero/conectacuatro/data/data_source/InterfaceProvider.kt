package com.zero.conectacuatro.data.data_source

import com.zero.conectacuatro.domain.model.Dot

interface InterfaceProvider {
    fun getDots(): MutableList<Dot>

    fun setDots(dotList: MutableList<Dot>)

    fun resetDots()
}