package com.creatinapps.fitnescoach.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.creatinapps.fitnescoach.data.room.model.DishDbModel

@Dao
interface DishDao {
    @Query("SELECT * FROM dishes")
    fun getAllDishes(): LiveData<List<DishDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDish(dishDbModel: DishDbModel)

    @Query("SELECT * FROM dishes WHERE id= :id")
    fun getDish(id: Int): DishDbModel

    @Query("DELETE FROM dishes")
    fun deleteAllDishes()
}
