package com.creatinapps.fitnescoach

data class FitMeal(
    val name: String,
    val calories: Int,
    val protein: Int,
    val fat: Int,
    val carbs: Int,
    val id: Int = 0,
    val imageFile: String
)