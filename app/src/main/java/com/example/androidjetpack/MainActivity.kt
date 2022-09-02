package com.example.androidjetpack

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.androidjetpack.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController
    private var navDestination: NavDestination? = null
    private lateinit var appBarConfiguration: AppBarConfiguration

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = configureNavController()
        appBarConfiguration = configureAppBar(navController)

        navController.addOnDestinationChangedListener { _, nd: NavDestination, _ ->
            configureActionBarDependingOnDestination(nd)
            navDestination = nd
        }

    }

    private fun configureNavController(): NavController{
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController
        return navController
    }

    private fun configureAppBar(navController: NavController): AppBarConfiguration{
        appBarConfiguration = AppBarConfiguration.Builder(navController.graph).build()
        appBarConfiguration = AppBarConfiguration(setOf(R.id.mainFragment, R.id.afterGameFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        return appBarConfiguration
    }

    private fun configureActionBarDependingOnDestination(nd: NavDestination){
        if (nd.id == R.id.splashFragment || nd.id == R.id.gameFragment) {
            supportActionBar?.hide()
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        } else {
            supportActionBar?.show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
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