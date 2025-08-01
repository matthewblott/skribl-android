package com.matthewblott.scribble

import android.os.Build
import android.webkit.CookieManager
import com.matthewblott.scribble.components.AlertComponent
import com.matthewblott.scribble.components.CreateNoteComponent
import com.matthewblott.scribble.components.DeleteNoteComponent
import com.matthewblott.scribble.components.NewNoteComponent
import com.matthewblott.scribble.components.ViewNotesFromIndexComponent
import com.matthewblott.scribble.components.SignInComponent
import com.matthewblott.scribble.components.SignOutComponent
import com.matthewblott.scribble.components.ViewNotesComponent
import com.matthewblott.scribble.fragments.WebFragment
import dev.hotwire.core.BuildConfig
import dev.hotwire.core.bridge.BridgeComponentFactory
import dev.hotwire.core.config.Hotwire
import dev.hotwire.core.turbo.config.PathConfiguration
import dev.hotwire.navigation.config.defaultFragmentDestination
import dev.hotwire.navigation.config.registerBridgeComponents
import dev.hotwire.navigation.config.registerFragmentDestinations

class Application : android.app.Application() {
  override fun onCreate() {
    super.onCreate()
    val cookieManager = CookieManager.getInstance()
    cookieManager.setAcceptCookie(true)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      cookieManager.flush()
    }    
    
    configureApp()
  }

  private fun configureApp() {
    val cookieManager = CookieManager.getInstance()
    val cookie = cookieManager.getCookie(Settings.baseUrl)

    Hotwire.loadPathConfiguration(
      context = this,
      location = PathConfiguration.Location(
        assetFilePath = "json/path-configuration.json",
//        remoteFileUrl = "${Demo.current.url}/configurations/android_v1.json"
      ),
      options = PathConfiguration.LoaderOptions(
        httpHeaders = mapOf("Cookie" to cookie)
      )
    )

    Hotwire.registerFragmentDestinations(
      WebFragment::class,
    )

    Hotwire.registerBridgeComponents(
      BridgeComponentFactory("alert", ::AlertComponent),
      BridgeComponentFactory("sign-in", ::SignInComponent),
      BridgeComponentFactory("sign-out", ::SignOutComponent),
      BridgeComponentFactory("view-notes", ::ViewNotesComponent),
      BridgeComponentFactory("view-notes-from-index", ::ViewNotesFromIndexComponent),
      BridgeComponentFactory("new-note", ::NewNoteComponent),
      BridgeComponentFactory("create-note", ::CreateNoteComponent),
      BridgeComponentFactory("delete-note", ::DeleteNoteComponent),
    ) 
    
    Hotwire.config.jsonConverter = dev.hotwire.core.bridge.KotlinXJsonConverter()
    Hotwire.config.debugLoggingEnabled = BuildConfig.DEBUG
    Hotwire.config.webViewDebuggingEnabled = BuildConfig.DEBUG
    Hotwire.config.applicationUserAgentPrefix = "Scribble;"
  }
}