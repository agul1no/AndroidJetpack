package com.example.androidjetpack.ui.scorefragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.androidjetpack.data.source.local.entities.LocalPerformance
import com.example.androidjetpack.data.source.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScoreFragmentViewModel @Inject constructor(
    private val localRepository: LocalRepository
): ViewModel() {

    fun getPlayerPerformancesLocal(playerID: Long): LiveData<List<LocalPerformance>>{
        return localRepository.getPlayerPerformancesLocal(playerID)
    }

}