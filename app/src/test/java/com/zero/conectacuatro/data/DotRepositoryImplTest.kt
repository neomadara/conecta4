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
    fun after() {
        fakeProvider.resetDots()
    }

    @Test
    fun `Should return an init Dot list`() {
        val dotsToInsert = defaultDotList()

        val initDotList = repository.getDots()

        assertThat(initDotList).isEqualTo(dotsToInsert)
    }

    @Test
    fun `Should modify the dot list`() {
        val dotList = defaultDotList()
        val dot = Dot(true, 0, 5,0)
        dotList[5] = dot

        repository.setDot(dot)

        assertThat(dotList).isEqualTo(repository.getDots())
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