package com.example.gameloop.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Vibrator
import android.util.DisplayMetrics
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.getSystemService
import androidx.navigation.fragment.findNavController
import com.example.gameloop.R
import com.example.gameloop.game.Game
import java.util.*
import kotlin.concurrent.schedule

class GameFragment : Fragment(){//R.layout.fragment_game) {

    //private var _binding: FragmentGameBinding? = null
    //private val binding get() = _binding!!
    private lateinit var game: Game

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       // _binding = FragmentGameBinding.inflate(inflater,container,false)
        val metrics = DisplayMetrics()
        activity?.windowManager?.defaultDisplay?.getMetrics(metrics)
        val screenWidth = metrics.widthPixels
        val screenHeight = metrics.heightPixels
        var vibrator = activity?.getSystemService<Vibrator>()
        game = Game(requireContext(), screenWidth, screenHeight, vibrator)


        return game
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //game = Game(requireContext())
        //_binding = FragmentGameBinding.bind(game)
        //_binding = binding

    }

    override fun onDestroyView() {
        //_binding = null
        super.onDestroyView()
    }

}