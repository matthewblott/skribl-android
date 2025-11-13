package com.matthewblott.scribble.components

import android.util.Log
import com.matthewblott.scribble.activities.SignInActivity
import dev.hotwire.core.bridge.BridgeComponent
import dev.hotwire.core.bridge.BridgeDelegate
import dev.hotwire.core.bridge.Message
import dev.hotwire.navigation.destinations.HotwireDestination
import dev.hotwire.navigation.fragments.HotwireFragment
import kotlinx.serialization.Serializable

class SignInSuccessComponent(
  name: String,
  private val bridgeDelegate: BridgeDelegate<HotwireDestination>
) : BridgeComponent<HotwireDestination>(name, bridgeDelegate) {
  private val fragment: HotwireFragment
    get() = bridgeDelegate.destination.fragment as HotwireFragment

  override fun onReceive(message: Message) {
    val data = message.data<MessageData>()
    
    when (message.event) {
      "authenticated" -> if (data != null) {
        com.matthewblott.scribble.Settings.userId = data.value.toInt()
        val activity = fragment.activity as SignInActivity
        activity.launchMainActivity()
      }
      else -> Log.w("Button Component", "Unknown event for message: $message")
    }
  }
  
  @Serializable
  data class MessageData(
    val value: String,
  )
}