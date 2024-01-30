package com.santimattius.android.startup.di

import android.content.Context
import com.santimattius.android.startup.service.CrashTrackerService
import com.santimattius.android.startup.service.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCrashTracker() = CrashTrackerService()

    @Provides
    @Singleton
    fun provideTaskRepository(@ApplicationContext context: Context) = TaskRepository(context)
}