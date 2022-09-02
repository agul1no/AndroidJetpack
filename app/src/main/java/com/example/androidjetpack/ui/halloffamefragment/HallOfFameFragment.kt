package com.example.androidjetpack.ui.halloffamefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.androidjetpack.R
import com.example.androidjetpack.databinding.FragmentHallOfFameBinding

class HallOfFameFragment : Fragment() {

    private var _binding: FragmentHallOfFameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHallOfFameBinding.inflate(inflater, container,false)

        //binding.tvHallOfFameTitle.paint.isUnderlineText = true

        binding.backButtonScoreFragment.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerScoreFragment_to_mainFragment)
        }

        return binding.root
    }

}