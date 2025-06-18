package com.matthewblott.scribble.fragments

import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.matthewblott.scribble.R
import com.matthewblott.scribble.activities.MainActivity
import dev.hotwire.navigation.destinations.HotwireDestinationDeepLink
import dev.hotwire.navigation.fragments.HotwireWebFragment

@HotwireDestinationDeepLink(uri = "hotwire://fragment/sign-in")
open class SignInFragment : HotwireWebFragment() {
  override fun onVisitCompleted(location: String, completedOffline: Boolean) {
    super.onVisitCompleted(location, completedOffline)
    val hotwireActivity = this.activity as MainActivity
    val bottomNav = hotwireActivity.findViewById<View>(R.id.bottom_nav_container)
    
    if(location.endsWith("sign_in")) {
      bottomNav.visibility = View.GONE
    }
    else {
      bottomNav.visibility = View.VISIBLE
    }
  
  } 

}