package com.matthewblott.skribl

import com.masilotti.bridgecomponents.shared.Bridgework
import com.matthewblott.skribl.components.AuthenticatedComponent
import com.matthewblott.skribl.components.DownloadComponent
import com.matthewblott.skribl.components.NoticeComponent
import com.matthewblott.skribl.components.UnauthenticatedComponent
import dev.hotwire.core.bridge.BridgeComponentFactory
import dev.hotwire.core.bridge.KotlinXJsonConverter
import dev.hotwire.core.config.Hotwire
import dev.hotwire.navigation.config.registerBridgeComponents

class Application : android.app.Application() {
  override fun onCreate() {
    super.onCreate()
    configureApp()
  }

  private fun configureApp() {
    Hotwire.config.jsonConverter = KotlinXJsonConverter()
    Hotwire.registerBridgeComponents(
      *Bridgework.coreComponents,
      BridgeComponentFactory("authenticated", ::AuthenticatedComponent),
//      BridgeComponentFactory("download", ::DownloadComponent),
//      BridgeComponentFactory("notice", ::NoticeComponent),
      BridgeComponentFactory("unauthenticated", ::UnauthenticatedComponent),
    )
  }
}