package com.example.gameloop.ui.scorefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.gameloop.R
import com.example.gameloop.adapter.LocalPerformanceRecyclerViewAdapter
import com.example.gameloop.databinding.FragmentAfterGameBinding
import com.example.gameloop.databinding.FragmentScoreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScoreFragment : Fragment() {

    private var _binding: FragmentScoreBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: LocalPerformanceRecyclerViewAdapter

    private val scoreFragmentViewModel by viewModels<ScoreFragmentViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentScoreBinding.inflate(inflater,container,false)

        initializeRecyclerView(scoreFragmentViewModel)

        scoreFragmentViewModel.getPlayerPerformancesLocal(playerID = 1).observe(viewLifecycleOwner,
            Observer { listLocalPerformances ->
                adapter.setData(listLocalPerformances)
        })

//        binding.backButtonScoreFragment.setOnClickListener {
//            findNavController().navigate(R.id.action_viewPagerScoreFragment_to_mainFragment)
//        }

        return binding.root
    }

    private fun initializeRecyclerView(scoreFragmentViewModel: ScoreFragmentViewModel): RecyclerView{
        adapter = LocalPerformanceRecyclerViewAdapter(scoreFragmentViewModel)
        binding.rvLocalPerformance.adapter = adapter
        return binding.rvLocalPerformance
    }

}