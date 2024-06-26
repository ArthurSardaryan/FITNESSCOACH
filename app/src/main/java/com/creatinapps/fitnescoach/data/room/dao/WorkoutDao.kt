package com.creatinapps.fitnescoach.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.creatinapps.fitnescoach.data.room.model.WorkoutDbModel

@Dao
interface WorkoutDao {
    @Query("SELECT * FROM workouts")
    fun getAllWorkouts(): LiveData<List<WorkoutDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkout(workoutDbModel: WorkoutDbModel)

    @Query("SELECT * FROM workouts WHERE id= :id")
    fun getWorkout(id: Int): WorkoutDbModel

    @Query("DELETE FROM workouts")
    fun deleteAllWorkouts()
}
