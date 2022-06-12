package com.zero.conectacuatro.data

import com.google.common.truth.Truth.assertThat
import com.zero.conectacuatro.data.data_source.FakeDotProvider
import com.zero.conectacuatro.data.repository.DotRepositoryImpl
import com.zero.conectacuatro.domain.model.Dot
import com.zero.conectacuatro.domain.repository.DotRepository
import org.junit.After
import org.junit.Before
import org.junit.Test

class DotRepositoryImplTest {

    private lateinit var repository: DotRepository
    private lateinit var fakeProvider: FakeDotProvider

    @Before
    fun setUp() {
        fakeProvider = FakeDotProvider()
        repository = DotRepositoryImpl(fakeProvider)
    }

    @After
    fun afterSetup() {
        repository.resetDots()
    }

    @Test
    fun `Should return an init Dot list`() {
        val defaultList = defaultDotList()

        val initDotList = repository.getDots()

        assertThat(initDotList).isEqualTo(defaultList)
    }

    @Test
    fun `Should modify the dot list`() {
        val defaultDotList = defaultDotList()
        val dot = Dot(true, 0, 5,1)
        defaultDotList[5] = dot

        repository.setDot(dot)
        val newDotList = repository.getDots()

        assertThat(defaultDotList).isEqualTo(newDotList)
    }
}

fun defaultDotList(): MutableList<Dot> {
    val dotsToInsert = mutableListOf<Dot>()
    repeat(7) { col ->
        repeat(6) { row ->
            dotsToInsert.add(Dot(false, col, row, 0))
        }
    }
    return dotsToInsert
}