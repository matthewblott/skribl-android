package com.matthewblott.scribble.main

import com.matthewblott.scribble.R
import com.matthewblott.scribble.Settings
import dev.hotwire.navigation.navigator.NavigatorConfiguration
import dev.hotwire.navigation.tabs.HotwireBottomTab

val notesTab = HotwireBottomTab(
  title = "Scribble",
  iconResId = R.drawable.posts,
  configuration = NavigatorConfiguration(
    name =  "main",
    startLocation = "${Settings.baseUrl}/${Settings.userId}/notes",
    navigatorHostId = R.id.notes_navigator_host,
  )
)

val newNoteTab = HotwireBottomTab(
  title = "New Note",
  iconResId = R.drawable.posts,
  configuration = NavigatorConfiguration(
    name =  "main",
    startLocation = "${Settings.baseUrl}/${Settings.userId}/notes/new",
    navigatorHostId = R.id.new_note_navigator_host,
  )
)


val settingsTab = HotwireBottomTab(
  title = "Settings",
  iconResId = R.drawable.settings,
  configuration = NavigatorConfiguration(
    name =  "settings",
    startLocation = "${Settings.baseUrl}/settings",
    navigatorHostId = R.id.settings_navigator_host,
  )
)

val mainTabs = listOf(
  notesTab, 
  newNoteTab,
  settingsTab,
)