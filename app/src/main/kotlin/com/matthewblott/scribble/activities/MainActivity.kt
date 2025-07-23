package com.matthewblott.scribble.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.matthewblott.scribble.R
import com.matthewblott.scribble.extensions.HotwireBottomNavigationController
import com.matthewblott.scribble.main.MainActivityViewModel
import com.matthewblott.scribble.main.mainTabs
import com.matthewblott.scribble.main.signInTab
import com.matthewblott.scribble.main.signInTabs
import com.matthewblott.scribble.main.signedInTabs
import dev.hotwire.navigation.activities.HotwireActivity
import dev.hotwire.navigation.tabs.navigatorConfigurations
import dev.hotwire.navigation.util.applyDefaultImeWindowInsets

const val baseURL = "http://10.0.2.2:3000"

class MainActivity : HotwireActivity() {
  lateinit var bottomNavigationController: HotwireBottomNavigationController
  lateinit var bottomNavigationController2: HotwireBottomNavigationController
  private val viewModel: MainActivityViewModel by viewModels()
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_main)
    findViewById<View>(R.id.root).applyDefaultImeWindowInsets()
//    getSharedPreferences("prefs", MODE_PRIVATE) 
    initializeBottomNavigationView()
  }

  override fun navigatorConfigurations() = mainTabs.navigatorConfigurations

//  fun launchSignInActivity() {
//    startActivity(Intent(this as Context, SignInActivity::class.java))
//    finish() // kill LauncherActivity so it won't show in back stack
//  }
  
  private fun initializeBottomNavigationView(){
    val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
//    val bottomNavigationView2 = findViewById<BottomNavigationView>(R.id.bottom_nav2)
    
    bottomNavigationController = HotwireBottomNavigationController(this, bottomNavigationView)
//    bottomNavigationController2 = HotwireBottomNavigationController(this, bottomNavigationView2)
//    bottomNavigationController.load(signInTabs, 0)
    bottomNavigationController.load(mainTabs, 0)
     
//    bottomNavigationController2.load(signedInTabs, 0)

    bottomNavigationView.visibility = View.VISIBLE
//    bottomNavigationView2.visibility = View.GONE  
    
//    bottomNavigationController.setOnTabSelectedListener { index, _ ->
//      viewModel.selectedTabIndex = index
//    }
  }
  
  
  
}