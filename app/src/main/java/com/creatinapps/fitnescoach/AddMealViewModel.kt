package com.creatinapps.fitnescoach

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class AddMealViewModel(application: Application) : AndroidViewModel(application) {
    private val dishDao = AppDatabase.getInstance(application).dishesDao()
    fun addMeal(
        meal: FitMeal
    ) {
        dishDao.insertDish(
            DishDbModel(
                name = meal.name,
                calories = meal.calories,
                protein = meal.protein,
                fat = meal.fat,
                carbs = meal.carbs,
                imageFile = meal.imageFile,
                id = 0
            )
        )
    }

}
