package com.example.androidjetpack.data.source.repository

import androidx.lifecycle.LiveData
import com.example.androidjetpack.data.source.local.dao.PerformanceLocalDatabase
import com.example.androidjetpack.data.source.local.entities.LocalPerformance
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val performanceLocalDatabase: PerformanceLocalDatabase
    ) : Repository {

    override suspend fun insertPerformance(performance: LocalPerformance) {
        performanceLocalDatabase.dao().insertPerformance(performance)
    }

    override fun getPlayerPerformancesLocal(playerID: Long): LiveData<List<LocalPerformance>> {
        return performanceLocalDatabase.dao().getPlayerPerformancesLocal(playerID)
    }
}