package com.matthewblott.scribble

import android.os.Build
import android.webkit.CookieManager
import com.matthewblott.scribble.components.ButtonComponent
import com.matthewblott.scribble.components.NewNoteComponent
import com.matthewblott.scribble.components.ViewNotesFromIndexComponent
import com.matthewblott.scribble.components.SignInComponent
import com.matthewblott.scribble.components.SignOutComponent
import com.matthewblott.scribble.components.ViewNotesComponent
import com.matthewblott.scribble.fragments.NotesFragment
import com.matthewblott.scribble.fragments.SettingsFragment
import com.matthewblott.scribble.fragments.WebFragment
import com.matthewblott.scribble.fragments.SignInFragment
import dev.hotwire.core.BuildConfig
import dev.hotwire.core.bridge.BridgeComponentFactory
import dev.hotwire.core.config.Hotwire
import dev.hotwire.core.turbo.config.PathConfiguration
import dev.hotwire.navigation.config.defaultFragmentDestination
import dev.hotwire.navigation.config.registerBridgeComponents
import dev.hotwire.navigation.config.registerFragmentDestinations
//import okhttp3.OkHttpClient
//import java.net.CookieHandler
//import java.net.CookieManager
//import java.net.CookiePolicy

class Application : android.app.Application() {
  override fun onCreate() {
    super.onCreate()
    val cookieManager = CookieManager.getInstance()
    cookieManager.setAcceptCookie(true)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      cookieManager.flush()
    }    
    
//    val cookieManager = CookieManager(null, CookiePolicy.ACCEPT_ALL)
//    CookieHandler.setDefault(cookieManager)
//    val okHttpClient = OkHttpClient.Builder()
//      .cookieJar(JavaNetCookieJar(cookieManager))
//      .build()
//
//    TurboSession.initializeDefault(this, okHttpClient)

//    val cookieManager = CookieManager.getInstance()
//    val cookie = cookieManager.getCookie(Endpoint.rootURL)
//
//    Hotwire.loadPathConfiguration(
//      context = this,
//      location = PathConfiguration.Location(
//        remoteFileUrl = Endpoint.Hotwire.pathConfigurationURL
//      ),
//      options = PathConfiguration.LoaderOptions(
//        httpHeaders = mapOf("Cookie" to cookie)
//      )
//    )
    
    configureApp()
  }

  private fun configureApp() {
    val rootURL = "http://10.0.2.2:3000"
    val cookieManager = CookieManager.getInstance()
    val cookie = cookieManager.getCookie(rootURL)

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

    Hotwire.defaultFragmentDestination = SignInFragment::class

    Hotwire.registerFragmentDestinations(
      WebFragment::class,
      SignInFragment::class,
      NotesFragment::class, 
      SettingsFragment::class,
    )

    Hotwire.registerBridgeComponents(
//      BridgeComponentFactory("button", ::ButtonComponent),
      BridgeComponentFactory("sign-in", ::SignInComponent),
      BridgeComponentFactory("sign-out", ::SignOutComponent),
      BridgeComponentFactory("view-notes", ::ViewNotesComponent),
        BridgeComponentFactory("view-notes-from-index", ::ViewNotesFromIndexComponent),
      BridgeComponentFactory("new-note", ::NewNoteComponent),
    ) 
    
    Hotwire.config.jsonConverter = dev.hotwire.core.bridge.KotlinXJsonConverter()

//    Hotwire.config.debugLoggingEnabled = BuildConfig.DEBUG
    Hotwire.config.webViewDebuggingEnabled = BuildConfig.DEBUG
//    Hotwire.config.applicationUserAgentPrefix = "Scribble;"
    
  }
}