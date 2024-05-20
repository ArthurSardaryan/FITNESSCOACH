package com.creatinapps.fitnescoach.domain.usecases

import com.creatinapps.fitnescoach.domain.model.FitWorkout
import com.creatinapps.fitnescoach.domain.repo.Repository
import javax.inject.Inject

class InsertWorkoutUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(workout: FitWorkout) {
        repository.insertWorkout(workout)
    }
}