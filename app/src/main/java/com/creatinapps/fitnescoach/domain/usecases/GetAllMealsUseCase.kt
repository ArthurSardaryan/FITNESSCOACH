package com.creatinapps.fitnescoach.domain.usecases

import com.creatinapps.fitnescoach.domain.repo.Repository
import javax.inject.Inject

class GetAllMealsUseCase @Inject constructor(
    private val repository: Repository
) {

    operator fun invoke() = repository.getAllMeals()
}