package com.example.gameloop.ui.scorefragment

import androidx.lifecycle.ViewModel
import com.example.gameloop.data.source.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScoreFragmentViewModel @Inject constructor(
    private val localRepository: LocalRepository
): ViewModel() {

}