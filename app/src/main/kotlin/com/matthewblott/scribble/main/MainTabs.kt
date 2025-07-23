package com.matthewblott.scribble.main

import com.matthewblott.scribble.R
import com.matthewblott.scribble.activities.baseURL
import dev.hotwire.navigation.navigator.NavigatorConfiguration
import dev.hotwire.navigation.tabs.HotwireBottomTab

val userId = 4

val signInTab = HotwireBottomTab(
  title = "Sign in",
  iconResId = R.drawable.posts,
  configuration = NavigatorConfiguration(
    name =  "sign-in",
    startLocation = "$baseURL/sign_in",
    navigatorHostId = R.id.sign_in_navigator_host,
  )
)

val notesTab = HotwireBottomTab(
  title = "Scribble",
  iconResId = R.drawable.posts,
  configuration = NavigatorConfiguration(
    name =  "notes",
    startLocation = "$baseURL/$userId/notes",
    navigatorHostId = R.id.notes_navigator_host,
  )
)

val newNoteTab = HotwireBottomTab(
  title = "New Note",
  iconResId = R.drawable.posts,
  configuration = NavigatorConfiguration(
    name =  "new-note",
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
    navigatorHostId = R.id.settings_navigator_host,
  )
)

val statusTab = HotwireBottomTab(
  title = "Status",
  iconResId = R.drawable.settings,
  configuration = NavigatorConfiguration(
    name =  "status",
    startLocation = "$baseURL/status",
    navigatorHostId = R.id.status_navigator_host,
  )
)

val signInTabs = listOf(
  signInTab,
)

val mainTabs = listOf(
  signInTab,
  notesTab, 
  newNoteTab,
  settingsTab,
  statusTab,
)

val signedInTabs = listOf(
  newNoteTab,
  notesTab,
  settingsTab,
  statusTab,
)
