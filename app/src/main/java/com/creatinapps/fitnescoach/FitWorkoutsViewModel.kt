package com.creatinapps.fitnescoach

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map

class FitWorkoutsViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getInstance(application).workoutsDao()

    fun getWorkouts(): LiveData<List<FitWorkout>> {
        return dao.getAllWorkouts().map { workouts ->
            workouts.map {
                FitWorkout(
                    name = it.name,
                    id = it.id,
                    reps = it.reps,
                    sets = it.sets,
                    type = it.type,
                    imageFile = it.imageFile
                )
            }
        }
    }
}
