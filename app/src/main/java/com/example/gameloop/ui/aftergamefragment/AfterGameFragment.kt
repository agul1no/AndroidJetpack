package com.example.gameloop.ui.aftergamefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.gameloop.R
import com.example.gameloop.data.source.local.entities.LocalPerformance
import com.example.gameloop.databinding.FragmentAfterGameBinding
import com.example.gameloop.game.GameLoop
import com.example.gameloop.game.Score
import com.example.gameloop.util.DateFormatter.Companion.millisToTimeInMinutesSeconds
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.math.roundToLong

@AndroidEntryPoint
class AfterGameFragment : Fragment() {

    private var _binding: FragmentAfterGameBinding? = null
    private val binding get() = _binding!!

    private val afterGameFragmentViewModel by viewModels<AfterGameFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAfterGameBinding.inflate(inflater,container,false)

        val playerID = 1  //later when the backend is implemented every player will get a playerID
        val playerName = "Agu"
        val score = Score.scoreCounter.roundToLong()
        val timePlayed = GameLoop.runningTime.millisToTimeInMinutesSeconds()
        val timeStamp = Calendar.getInstance().timeInMillis
        val localPerformance = LocalPerformance(
            performanceID = 0,
            playerID = playerID.toLong(),
            playerName = playerName,
            timePlayed = timePlayed,
            score = score.toInt(),
            timeStamp = timeStamp
        )

        afterGameFragmentViewModel.insertLocalPerformance(localPerformance)

        binding.tvScorePlayed.text = "Score:  ${Score.scoreCounter.roundToLong()} points"

        val playedTime = GameLoop.runningTime.millisToTimeInMinutesSeconds()
        binding.tvTimePlayed.text = "Time played:  $playedTime min"

        binding.backButtonAfterGame.setOnClickListener {
            findNavController().navigate(R.id.action_afterGameFragment_to_mainFragment)
        }

        return binding.root
    }

}