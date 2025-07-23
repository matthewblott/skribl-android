package com.matthewblott.scribble.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.CookieManager
import androidx.appcompat.app.AppCompatActivity

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
    // Replace with real auth logic (e.g. FirebaseAuth, SharedPreferences, etc.)
//    return getSharedPreferences("prefs", MODE_PRIVATE)
//      .getBoolean("logged_in", false)

    val cookieManager = CookieManager.getInstance()
    val cookies = cookieManager.getCookie("http://10.0.2.2")

//    val isSignedIn = cookies != null &&
//      cookies.contains("_web_session=") &&
//      cookies.contains("session_token=") &&
//      cookies.contains("user_id=") &&
//      !cookies.contains("_web_session=;") && // Not empty
//      !cookies.contains("session_token=;") && // Not empty
//      !cookies.contains("user_id=;") // Not empty

    val isSignedIn = cookies != null &&
      cookies.contains("session_token=") &&
      !cookies.contains("session_token=;") // Not empty
    
    return isSignedIn 
  }
}