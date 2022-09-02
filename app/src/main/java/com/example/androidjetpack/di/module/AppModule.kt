package com.example.androidjetpack.di.module

import android.app.Application
import androidx.room.Room
import com.example.androidjetpack.data.source.local.dao.PerformanceLocalDatabase
import com.example.androidjetpack.data.source.repository.LocalRepository
import com.example.androidjetpack.data.source.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application) : PerformanceLocalDatabase =
        Room.databaseBuilder(application, PerformanceLocalDatabase::class.java, "local_performances_database")
            .build()

    @Provides
    @Singleton
    fun provideRepository(localPerformancesDatabase: PerformanceLocalDatabase) : Repository =
        LocalRepository(localPerformancesDatabase)
}