package com.creatinapps.fitnescoach.domain.repo

import androidx.lifecycle.LiveData
import com.creatinapps.fitnescoach.domain.model.FitMeal
import com.creatinapps.fitnescoach.domain.model.FitWorkout

interface Repository {
    fun getAllWorkouts(): LiveData<List<FitWorkout>>

    fun getAllMeals(): LiveData<List<FitMeal>>

    suspend fun insertWorkout(workout: FitWorkout)

    suspend fun insertMeal(meal: FitMeal)
}