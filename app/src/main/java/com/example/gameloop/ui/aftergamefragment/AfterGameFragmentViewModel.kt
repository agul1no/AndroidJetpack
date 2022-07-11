package com.example.gameloop.ui.aftergamefragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gameloop.data.source.local.entities.LocalPerformance
import com.example.gameloop.data.source.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AfterGameFragmentViewModel @Inject constructor(
    private val localRepository: LocalRepository
): ViewModel(){

    fun insertLocalPerformance(localPerformance: LocalPerformance){
        viewModelScope.launch (Dispatchers.IO){
            localRepository.insertPerformance(localPerformance)
        }
    }

}