package com.creatinapps.fitnescoach.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.creatinapps.fitnescoach.domain.usecases.GetAllMealsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FitMealsViewModel @Inject constructor(
    private val getAllMealsUseCase: GetAllMealsUseCase
) : ViewModel() {
    fun getDishes() = getAllMealsUseCase()
}