package com.creatinapps.fitnescoach.domain.model

data class FitWorkout(
    val name: String,
    val sets: Int,
    val reps: Int,
    val id: Int = 0,
    val type: String,
    val imageFile: String
)