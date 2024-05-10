package com.creatinapps.fitnescoach

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.map

class FitMealsViewModel(application: Application) : AndroidViewModel(application) {
    private val dishDao = AppDatabase.getInstance(application).dishesDao()
    fun getDishes(): LiveData<List<FitMeal>> {
        return dishDao.getAllDishes().map { dishDbModels ->
            dishDbModels.map {
                FitMeal(
                    name = it.name,
                    calories = it.calories,
                    protein = it.protein,
                    fat = it.fat,
                    carbs = it.carbs,
                    id = it.id,
                    imageFile = it.imageFile
                )
            }
        }
    }
}