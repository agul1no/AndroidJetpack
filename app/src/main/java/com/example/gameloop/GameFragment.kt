package com.example.gameloop

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gameloop.databinding.FragmentGameBinding
import com.example.gameloop.databinding.FragmentMainBinding

class GameFragment : Fragment(), Runnable {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private var isRunning = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGameBinding.inflate(inflater,container,false)

        run()

        return binding.root
    }

    override fun run() {

        while (isRunning){

        }
    }

}