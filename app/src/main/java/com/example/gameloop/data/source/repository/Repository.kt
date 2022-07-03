package com.example.gameloop.data.source.repository

import androidx.lifecycle.LiveData
import com.example.gameloop.data.model.NetworkPerformance
import com.example.gameloop.data.source.local.entities.LocalPerformance

interface Repository {

    suspend fun insertPerformance(performance: LocalPerformance)

    suspend fun getPlayerPerformancesLocal(playerID: Long) : LiveData<List<LocalPerformance>>
}