package com.creatinapps.fitnescoach.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creatinapps.fitnescoach.domain.model.FitWorkout
import com.creatinapps.fitnescoach.domain.usecases.InsertWorkoutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddWorkoutViewModel @Inject constructor(
    private val insertWorkoutUseCase: InsertWorkoutUseCase
) : ViewModel() {
    fun addWorkout(
        workout: FitWorkout
    ) {
        viewModelScope.launch {
            insertWorkoutUseCase(workout)
        }
    }

}
