package com.example.gameloop

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.example.gameloop.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var navDestination: NavDestination? = null

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        var navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
            if (nd.id == R.id.splashFragment || nd.id == R.id.gameFragment) {
                supportActionBar?.hide()
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

                //window.insetsController?.hide(WindowInsets.Type.statusBars())
            } else {
                supportActionBar?.show()
                //window.insetsController?.show(WindowInsets.Type.statusBars())
            }
            navDestination = nd
        }

    }

    override fun onBackPressed() {
        when(navDestination?.id){
            R.id.mainFragment -> { finish() }
            R.id.splashFragment -> { finish() }
            R.id.viewPagerScoreFragment -> { super.onBackPressed() }
            R.id.gameFragment -> { super.onBackPressed() }
            R.id.afterGameFragment -> { super.onBackPressed() }
        }

    }
}