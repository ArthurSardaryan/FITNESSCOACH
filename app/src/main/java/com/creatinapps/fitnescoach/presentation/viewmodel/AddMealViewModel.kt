package com.creatinapps.fitnescoach.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creatinapps.fitnescoach.domain.model.FitMeal
import com.creatinapps.fitnescoach.domain.usecases.InsertMealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddMealViewModel @Inject constructor(
    private val insertMealUseCase: InsertMealUseCase
) : ViewModel() {
    fun addMeal(
        meal: FitMeal
    ) {
        viewModelScope.launch {
            insertMealUseCase(meal)
        }
    }

}
