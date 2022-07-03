package com.example.gameloop.viewpagerscreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.gameloop.R
import com.example.gameloop.databinding.FragmentAfterGameBinding
import com.example.gameloop.databinding.FragmentScoreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScoreFragment : Fragment() {

    private var _binding: FragmentScoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentScoreBinding.inflate(inflater,container,false)

        //binding.tvScoreTitle.paint.isUnderlineText = true

        binding.backButtonScoreFragment.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerScoreFragment_to_mainFragment)
        }

        return binding.root
    }

}