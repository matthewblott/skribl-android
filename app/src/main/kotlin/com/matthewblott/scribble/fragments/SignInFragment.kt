package com.matthewblott.scribble.fragments

import dev.hotwire.navigation.destinations.HotwireDestinationDeepLink
import dev.hotwire.navigation.fragments.HotwireWebFragment

@HotwireDestinationDeepLink(uri = "hotwire://fragment/sign-in")
open class SignInFragment : HotwireWebFragment() {
  override fun onVisitCompleted(location: String, completedOffline: Boolean) {
    super.onVisitCompleted(location, completedOffline)
//    val hotwireActivity = this.activity as MainActivity
//    val bottomNav = hotwireActivity.findViewById<BottomNavigationView>(R.id.bottom_nav)

    
    
//    if(location.endsWith("sign_in")) {
//      bottomNav.visibility = View.GONE
//    }
//    else {
//      bottomNav.visibility = View.VISIBLE
//    }
  
  } 

}