package com.example.androidjetpack.data.source.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "performance_table")
data class LocalPerformance(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "performance_id")
    val performanceID: Long,

    @ColumnInfo(name = "player_id")
    val playerID: Long,

    @ColumnInfo(name = "player_name")
    val playerName: String,

    @ColumnInfo(name = "score")
    val score: Int,

    @ColumnInfo(name = "time_played")
    val timePlayed: String,

    @ColumnInfo(name = "time_stamp")
    val timeStamp: Long
)
