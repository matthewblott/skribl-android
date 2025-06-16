package com.matthewblott.scribble.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
    return getSharedPreferences("prefs", MODE_PRIVATE)
      .getBoolean("logged_in", false)
  }
}