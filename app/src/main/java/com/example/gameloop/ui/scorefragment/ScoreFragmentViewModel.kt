package com.example.gameloop.ui.scorefragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gameloop.data.source.local.entities.LocalPerformance
import com.example.gameloop.data.source.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScoreFragmentViewModel @Inject constructor(
    private val localRepository: LocalRepository
): ViewModel() {

    fun getPlayerPerformancesLocal(playerID: Long): LiveData<List<LocalPerformance>>{
        return localRepository.getPlayerPerformancesLocal(playerID)
    }

}