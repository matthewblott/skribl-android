package com.matthewblott.scribble.main

import com.matthewblott.scribble.R
import com.matthewblott.scribble.activities.baseURL
import dev.hotwire.navigation.navigator.NavigatorConfiguration
import dev.hotwire.navigation.tabs.HotwireBottomTab

const val userId = 4

val notesTab = HotwireBottomTab(
  title = "Scribble",
  iconResId = R.drawable.posts,
  configuration = NavigatorConfiguration(
    name =  "main",
    startLocation = "$baseURL/$userId/notes",
    navigatorHostId = R.id.notes_navigator_host,
  )
)

val newNoteTab = HotwireBottomTab(
  title = "New Note",
  iconResId = R.drawable.posts,
  configuration = NavigatorConfiguration(
    name =  "main",
    startLocation = "$baseURL/$userId/notes/new",
    navigatorHostId = R.id.new_note_navigator_host,
  )
)


val settingsTab = HotwireBottomTab(
  title = "Settings",
  iconResId = R.drawable.settings,
  configuration = NavigatorConfiguration(
    name =  "settings",
    startLocation = "$baseURL/settings",
//    startLocation = "$baseURL/signed_in",
    navigatorHostId = R.id.settings_navigator_host,
  )
)

val mainTabs = listOf(
  notesTab, 
  newNoteTab,
  settingsTab,
)