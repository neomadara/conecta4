package com.zero.conectacuatro.data.repository

import com.zero.conectacuatro.data.data_source.InterfaceProvider
import com.zero.conectacuatro.domain.model.Dot
import com.zero.conectacuatro.domain.repository.DotRepository

class DotRepositoryImpl(
    private val sourceData: InterfaceProvider): DotRepository {

    override fun getDots(): MutableList<Dot> {
        return sourceData.getDots()
    }

    override fun setDot(dot: Dot) {
        TODO("Not yet implemented")
    }

    override fun setDots(dots: MutableList<Dot>) {
        TODO("Not yet implemented")
    }

}