package com.zero.conectacuatro.domain.model

data class Dot(
    var isActive: Boolean = false,
    val column: Int,
    val row: Int,
    val playerId: Int
)