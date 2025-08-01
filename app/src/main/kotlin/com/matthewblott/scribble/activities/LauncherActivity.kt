package com.matthewblott.scribble.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.CookieManager
import androidx.appcompat.app.AppCompatActivity
import com.matthewblott.scribble.Settings

class LauncherActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val isLoggedIn = checkIfUserIsLoggedIn() // implement this

    val nextActivity = if (isLoggedIn) {
      MainActivity::class.java
    } else {
      SignInActivity::class.java
    }

    startActivity(Intent(this, nextActivity))
    finish() // kill LauncherActivity so it won't show in back stack
  }

  private fun checkIfUserIsLoggedIn(): Boolean {
    val cookieManager = CookieManager.getInstance()
    val cookies = cookieManager.getCookie(Settings.baseUrl)

    val isSignedIn = cookies != null &&
      cookies.contains("session_token=") &&
      !cookies.contains("session_token=;") // Not empty
    
    return isSignedIn 
  }
}