package com.matthewblott.scribble.fragments

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.matthewblott.scribble.R
import com.matthewblott.scribble.activities.MainActivity
import dev.hotwire.navigation.destinations.HotwireDestinationDeepLink
import dev.hotwire.navigation.fragments.HotwireWebFragment

@HotwireDestinationDeepLink(uri = "hotwire://fragment/settings")
open class SettingsFragment : HotwireWebFragment() {

  override fun onVisitCompleted(location: String, completedOffline: Boolean) {
    super.onVisitCompleted(location, completedOffline)
    val hotwireActivity = this.activity as MainActivity
    val bottomNav = hotwireActivity.findViewById<BottomNavigationView>(R.id.bottom_nav)

//    if(location.endsWith("sign_in")) {
//      bottomNav.visibility = View.GONE
//    }
//    else {
//      bottomNav.visibility = View.VISIBLE
//    }

  }
  override fun onResume() {
    super.onResume()
//    val hotwireActivity = this.activity as MainActivity
//    val bottomNav = hotwireActivity.findViewById<BottomNavigationView>(R.id.bottom_nav)
//    bottomNav.visibility = View.GONE
//    val controller = (activity as? MainActivity)?.bottomNavigationController
//    controller?.hideNavigation() 
  }
}
