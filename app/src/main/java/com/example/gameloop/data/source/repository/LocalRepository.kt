package com.example.gameloop.data.source.repository

import androidx.lifecycle.LiveData
import com.example.gameloop.data.model.NetworkPerformance
import com.example.gameloop.data.source.local.dao.PerformanceLocalDatabase
import com.example.gameloop.data.source.local.entities.LocalPerformance
import javax.inject.Inject

class LocalRepository @Inject constructor(private val performanceLocalDatabase: PerformanceLocalDatabase) : Repository {
    override suspend fun insertPerformance(performance: LocalPerformance) {
        performanceLocalDatabase.dao().insertPerformance(performance)
    }

    override suspend fun getPlayerPerformancesLocal(playerID: Long): LiveData<List<LocalPerformance>> {
        return performanceLocalDatabase.dao().getPlayerPerformancesLocal(playerID)
    }

    override suspend fun getPerformancesRemote(): LiveData<List<NetworkPerformance>> {
        return performanceLocalDatabase.dao().getPerformancesRemote()
    }
}