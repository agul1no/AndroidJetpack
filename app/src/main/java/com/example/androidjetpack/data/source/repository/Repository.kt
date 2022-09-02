package com.example.androidjetpack.data.source.repository

import androidx.lifecycle.LiveData
import com.example.androidjetpack.data.source.local.entities.LocalPerformance

interface Repository {

    suspend fun insertPerformance(performance: LocalPerformance)

    fun getPlayerPerformancesLocal(playerID: Long) : LiveData<List<LocalPerformance>>
}