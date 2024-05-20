package com.creatinapps.fitnescoach.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.creatinapps.fitnescoach.data.room.dao.DishDao
import com.creatinapps.fitnescoach.data.room.dao.WorkoutDao
import com.creatinapps.fitnescoach.data.room.model.DishDbModel
import com.creatinapps.fitnescoach.data.room.model.WorkoutDbModel
import com.creatinapps.fitnescoach.domain.model.FitMeal
import com.creatinapps.fitnescoach.domain.model.FitWorkout
import com.creatinapps.fitnescoach.domain.repo.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dishesDao: DishDao,
    private val workoutsDao: WorkoutDao
): Repository {
    override fun getAllWorkouts(): LiveData<List<FitWorkout>> {
        return workoutsDao.getAllWorkouts().map {
            it.map {
                FitWorkout(
                    id = it.id,
                    name = it.name,
                    sets = it.sets,
                    reps = it.reps,
                    type = it.type,
                    imageFile = it.imageFile,
                )
            }
        }
    }

    override fun getAllMeals(): LiveData<List<FitMeal>> {
        return dishesDao.getAllDishes().map {
            it.map {
                FitMeal(
                    id = it.id,
                    name = it.name,
                    imageFile = it.imageFile,
                    fat = it.fat,
                    protein = it.protein,
                    carbs = it.carbs,
                    calories = it.calories
                )
            }
        }
    }

    override suspend fun insertWorkout(workout: FitWorkout) {
        return workoutsDao.insertWorkout(
            WorkoutDbModel(
                id = workout.id,
                name = workout.name,
                sets = workout.sets,
                reps = workout.reps,
                type = workout.type,
                imageFile = workout.imageFile
            )
        )
    }

    override suspend fun insertMeal(meal: FitMeal) {
        return dishesDao.insertDish(
            DishDbModel(
                id = meal.id,
                name = meal.name,
                imageFile = meal.imageFile,
                fat = meal.fat,
                protein = meal.protein,
                carbs = meal.carbs,
                calories = meal.calories
            )
        )
    }
}