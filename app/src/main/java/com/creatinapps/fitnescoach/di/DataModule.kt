package com.creatinapps.fitnescoach.di

import android.content.Context
import com.creatinapps.fitnescoach.data.repo.RepositoryImpl
import com.creatinapps.fitnescoach.data.room.db.AppDatabase
import com.creatinapps.fitnescoach.domain.repo.Repository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) = AppDatabase.getInstance(context)

    @Provides
    fun provideDishesDao(appDatabase: AppDatabase) = appDatabase.dishesDao()

    @Provides
    fun provideWorkoutsDao(appDatabase: AppDatabase) = appDatabase.workoutsDao()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(impl: RepositoryImpl): Repository
}