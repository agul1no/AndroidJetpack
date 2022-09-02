package com.example.androidjetpack.ui.aftergamefragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidjetpack.data.source.local.entities.LocalPerformance
import com.example.androidjetpack.data.source.repository.LocalRepository
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