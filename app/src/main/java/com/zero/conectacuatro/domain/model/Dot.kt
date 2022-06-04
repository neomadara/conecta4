package com.zero.conectacuatro.domain.model

data class Dot(
    val isActive: Boolean = false,
    val column: Int,
    val row: Int,
    val playerId: Int
)