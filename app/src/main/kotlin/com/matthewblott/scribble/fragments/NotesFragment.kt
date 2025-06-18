package com.matthewblott.scribble.fragments

import android.view.View
import com.matthewblott.scribble.R
import com.matthewblott.scribble.activities.MainActivity
import dev.hotwire.navigation.destinations.HotwireDestinationDeepLink
import dev.hotwire.navigation.fragments.HotwireWebFragment

@HotwireDestinationDeepLink(uri = "hotwire://fragment/notes")
open class NotesFragment : HotwireWebFragment(){
  override fun onVisitCompleted(location: String, completedOffline: Boolean) {
    super.onVisitCompleted(location, completedOffline)
    val hotwireActivity = this.activity as MainActivity
    val bottomNav = hotwireActivity.findViewById<View>(R.id.bottom_nav_container)
    bottomNav.visibility = View.VISIBLE
  }

}