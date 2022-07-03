package com.example.gameloop.data.source.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gameloop.data.source.local.entities.LocalPerformance

@Database(entities = [LocalPerformance::class], version = 1, exportSchema = false)
abstract class PerformanceLocalDatabase : RoomDatabase(){

    abstract fun dao(): GameLoopDao
}