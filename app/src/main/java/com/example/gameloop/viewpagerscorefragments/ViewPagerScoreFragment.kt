package com.example.gameloop.viewpagerscorefragments

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.gameloop.R
import com.example.gameloop.databinding.FragmentViewPagerScoreBinding
import com.example.gameloop.viewpagerscreens.HallOfFameFragment
import com.example.gameloop.viewpagerscreens.ScoreFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_view_pager_score.*

class ViewPagerScoreFragment: Fragment() {

    private var _binding: FragmentViewPagerScoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentViewPagerScoreBinding.inflate(inflater,container,false)

        val fragmentList = arrayListOf<Fragment>(
            ScoreFragment(),
            HallOfFameFragment()
        )

        val adapter = ViewPagerScoreAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding.viewPager.adapter = adapter

        binding.tabs.addTab(binding.tabs.newTab().setText("Your Top Score"), 0)
        binding.tabs.addTab(binding.tabs.newTab().setText("Hall of Fame"),1)
        binding.tabs.background.setTint(resources.getColor(R.color.light_blue_grey))

        TabLayoutMediator(binding.tabs,binding.viewPager){ tab, position ->
            when (position){
                0 -> tab.text = "Your Top Score"
                1 -> tab.text = "Hall of Fame"
            }
        }.attach()

        return binding.root
    }
}