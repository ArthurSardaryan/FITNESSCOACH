package com.creatinapps.fitnescoach

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class AddWorkoutViewModel(application: Application): AndroidViewModel(application) {
    private val workoutDao = AppDatabase.getInstance(application).workoutsDao()
    fun addWorkout(
        workout: FitWorkout
    ) {
        workoutDao.insertWorkout(
            WorkoutDbModel(
                name = workout.name,
                reps = workout.reps,
                sets = workout.sets,
                type = workout.type,
                imageFile = workout.imageFile
            )
        )
    }

}
