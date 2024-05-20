package com.creatinapps.fitnescoach.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.creatinapps.fitnescoach.domain.usecases.GetAllWorkoutsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FitWorkoutsViewModel @Inject constructor(
    private val getAllWorkoutsUseCase: GetAllWorkoutsUseCase
) : ViewModel()
{
    fun getWorkouts() = getAllWorkoutsUseCase()
}
