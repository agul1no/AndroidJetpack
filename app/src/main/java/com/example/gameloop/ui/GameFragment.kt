package com.example.gameloop.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Vibrator
import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.getSystemService
import androidx.navigation.fragment.findNavController
import com.example.gameloop.R
import com.example.gameloop.game.Game

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

        game = Game(requireContext(), screenWidth, screenHeight, vibrator,this)

        return game
    }

    fun moveToTheAfterGameFragment(){
        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_gameFragment_to_afterGameFragment)
                                                    }, 1600)
    }

    override fun onResume() {
        super.onResume()
        game.resumeGame()
    }

    override fun onPause() {
        super.onPause()
        game.pauseGame()
    }

}