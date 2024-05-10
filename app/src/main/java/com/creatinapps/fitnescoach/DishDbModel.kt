package com.creatinapps.fitnescoach

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dishes")
data class DishDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val calories: Int,
    val protein: Int,
    val fat: Int,
    val carbs: Int,
    val imageFile: String
) {
}
