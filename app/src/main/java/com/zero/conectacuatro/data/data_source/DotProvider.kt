package com.zero.conectacuatro.data.data_source

import com.zero.conectacuatro.domain.model.Dot

class DotProvider: InterfaceProvider {
    companion object {
        var dots:MutableList<Dot> = mutableListOf()
    }

    override fun getDots(): MutableList<Dot> {
        TODO("Not yet implemented")
    }

    override fun setDots(dotList: MutableList<Dot>) {
        TODO("Not yet implemented")
    }
}