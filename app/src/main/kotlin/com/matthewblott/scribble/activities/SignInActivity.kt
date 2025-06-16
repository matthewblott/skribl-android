package com.matthewblott.scribble.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import com.matthewblott.scribble.R
import dev.hotwire.navigation.activities.HotwireActivity
import dev.hotwire.navigation.navigator.NavigatorConfiguration
import dev.hotwire.navigation.util.applyDefaultImeWindowInsets

class SignInActivity : HotwireActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContentView(R.layout.activity_sign_in)
    findViewById<View>(R.id.main_nav_host).applyDefaultImeWindowInsets()
//    getSharedPreferences("prefs", MODE_PRIVATE) 
//    startActivity(0)
  }

  fun launchMainActivity() {
    startActivity(Intent(this as Context, MainActivity::class.java))
    finish() // kill LauncherActivity so it won't show in back stack
  }
  
  override fun navigatorConfigurations() = listOf(
    NavigatorConfiguration(
      name = "main",
      startLocation = rootURL,
      navigatorHostId = R.id.main_nav_host
    )
  )

  companion object {
    const val rootURL = "http://10.0.2.2:3000"
  }

}
  