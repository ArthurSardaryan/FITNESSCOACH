package com.creatinapps.fitnescoach

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "workouts")
data class WorkoutDbModel(
    val name: String,
    val sets: Int,
    val reps: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val type: String,
    val imageFile: String
)