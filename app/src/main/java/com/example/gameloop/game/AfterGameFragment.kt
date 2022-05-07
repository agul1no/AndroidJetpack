package com.example.gameloop.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.gameloop.R
import com.example.gameloop.databinding.FragmentAfterGameBinding
import kotlin.math.roundToLong

class AfterGameFragment : Fragment() {

    private var _binding: FragmentAfterGameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAfterGameBinding.inflate(inflater,container,false)

        binding.tvScorePlayed.text = "Score: ${Score.scoreCounter.roundToLong()}"

        binding.backButtonAfterGame.setOnClickListener {
            findNavController().navigate(R.id.action_afterGameFragment_to_mainFragment)
        }

        return binding.root
    }

}