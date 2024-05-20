package com.creatinapps.fitnescoach.domain.usecases

import com.creatinapps.fitnescoach.domain.model.FitMeal
import com.creatinapps.fitnescoach.domain.repo.Repository
import javax.inject.Inject

class InsertMealUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(meal: FitMeal) {
        repository.insertMeal(meal)
    }
}