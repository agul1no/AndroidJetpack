package com.example.gameloop.data.model

data class NetworkPerformance(
    val performanceID: Long,
    val playerID: Long,
    val playerName: String,
    val score: Int,
    val timePlayed: String,
    val timeStamp: Long
)
