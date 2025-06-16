package com.matthewblott.scribble.fragments

import dev.hotwire.navigation.destinations.HotwireDestinationDeepLink
import dev.hotwire.navigation.fragments.HotwireWebFragment

@HotwireDestinationDeepLink(uri = "hotwire://fragment/notes")
open class NotesFragment : HotwireWebFragment()