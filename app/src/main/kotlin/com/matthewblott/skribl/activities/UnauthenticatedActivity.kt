package com.matthewblott.skribl.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.matthewblott.skribl.R
import dev.hotwire.navigation.navigator.NavigatorConfiguration
import dev.hotwire.navigation.util.applyDefaultImeWindowInsets

class UnauthenticatedActivity: BaseHotwireActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_unauthenticated)
    findViewById<View>(R.id.unauthenticated_nav_host).applyDefaultImeWindowInsets()
  }

  fun launchAuthenticatedActivity() {
    startActivity(Intent(this as Context, AuthenticatedActivity::class.java))
    finish() // kill SignInActivity
  }
  
  override fun navigatorConfigurations() = listOf(
    NavigatorConfiguration(
      name = "main",
      startLocation = "http://10.0.2.2:3000",
      navigatorHostId = R.id.unauthenticated_nav_host
    )
  )
}