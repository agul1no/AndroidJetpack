package com.example.gameloop.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gameloop.R
import com.example.gameloop.databinding.FragmentAfterGameBinding
import com.example.gameloop.game.GameLoop
import com.example.gameloop.game.Score
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.roundToLong

class AfterGameFragment : Fragment() {

    private var _binding: FragmentAfterGameBinding? = null
    private val binding get() = _binding!!

    val SECONDS_IN_ONE_MINUTE = 60.0
    val MILLISEC_IN_ONE_SECOND = 1000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAfterGameBinding.inflate(inflater,container,false)

        binding.tvScorePlayed.text = "Score:  ${Score.scoreCounter.roundToLong()} points"

        val playedTime = GameLoop.runningTime.formatTimeToString()
        binding.tvTimePlayed.text = "Time played:  ${playedTime} min"

        binding.backButtonAfterGame.setOnClickListener {
            findNavController().navigate(R.id.action_afterGameFragment_to_mainFragment)
        }

        return binding.root
    }

    private fun transformMilliToSeconds(timeSeconds: Long): Long {
        return (timeSeconds / MILLISEC_IN_ONE_SECOND)
    }

    fun Long.formatTimeToString(): String {
        var playedTimeInStringFormat : String = ":"
        val playedTime = transformMilliToSeconds(this)

        val division = playedTime / SECONDS_IN_ONE_MINUTE
        val remainder = playedTime.rem(SECONDS_IN_ONE_MINUTE)

        if (remainder < 10){
            playedTimeInStringFormat = "${BigDecimal(division).setScale(0, RoundingMode.DOWN)}:0${BigDecimal(remainder).setScale(0, RoundingMode.DOWN)}"
        }
        if (remainder >= 10){
            playedTimeInStringFormat = "${BigDecimal(division).setScale(0, RoundingMode.DOWN)}:${BigDecimal(remainder).setScale(0, RoundingMode.DOWN)}"
        }
        return playedTimeInStringFormat
    }
}