package com.example.gameloop.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "performance_table")
data class LocalPerformance(
    @PrimaryKey(autoGenerate = true)
    val performanceID: Long,
    val playerID: Long,
    val playerName: String,
    val score: Int,
    val timePlayed: String,
    val timeStamp: Long
)
