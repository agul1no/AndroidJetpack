package com.example.gameloop.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gameloop.R
import com.example.gameloop.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater,container,false)

        binding.startButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_gameFragment)
        }

        binding.scoreButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_viewPagerScoreFragment)
        }

        return binding.root
    }

}