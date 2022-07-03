package com.example.gameloop.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gameloop.data.model.NetworkPerformance
import com.example.gameloop.data.source.local.entities.LocalPerformance

@Dao
interface GameLoopDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPerformance(performance: LocalPerformance)

    @Query ("SELECT * FROM performance_table WHERE playerID LIKE :playerID ORDER BY score DESC LIMIT 20")
    fun getPlayerPerformancesLocal(playerID: Long) : LiveData<List<LocalPerformance>>

}