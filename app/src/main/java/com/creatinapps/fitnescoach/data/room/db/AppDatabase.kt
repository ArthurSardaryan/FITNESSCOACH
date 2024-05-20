package com.creatinapps.fitnescoach.data.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.creatinapps.fitnescoach.data.room.dao.DishDao
import com.creatinapps.fitnescoach.data.room.dao.WorkoutDao
import com.creatinapps.fitnescoach.data.room.model.DishDbModel
import com.creatinapps.fitnescoach.data.room.model.WorkoutDbModel

@Database(entities = [DishDbModel::class, WorkoutDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dishesDao(): DishDao
    abstract fun workoutsDao(): WorkoutDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "dish_items.db"

        fun getInstance(application: Context): AppDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                ).allowMainThreadQueries()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}