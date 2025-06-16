package com.matthewblott.scribble.components

import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.matthewblott.scribble.R
import com.matthewblott.scribble.activities.SignInActivity
import dev.hotwire.core.bridge.BridgeComponent
import dev.hotwire.core.bridge.BridgeDelegate
import dev.hotwire.core.bridge.Message
import dev.hotwire.navigation.destinations.HotwireDestination
import dev.hotwire.navigation.fragments.HotwireFragment
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
class ButtonComponent(
  name: String,
  private val bridgeDelegate: BridgeDelegate<HotwireDestination>
) : BridgeComponent<HotwireDestination>(name, bridgeDelegate) {
  private val buttonId = 1
  private val fragment: HotwireFragment
    get() = bridgeDelegate.destination.fragment as HotwireFragment

  override fun onReceive(message: Message) {
    when (message.event) {
      "connect" -> addButton(message)
      "disconnect" -> removeButton()
      else -> Log.w("Button Component", "Unknown event for message: $message")
    }
  }

  private fun addButton(message: Message) {
    val data = message.data<MessageData>() ?: return
    removeButton()

    val marginInPx = TypedValue.applyDimension(
      TypedValue.COMPLEX_UNIT_DIP,
      16f,
      fragment.requireContext().resources.displayMetrics
    ).toInt()

    val composeView = ComposeView(fragment.requireContext()).apply {
      id = buttonId
      setContent {
        ToolbarButton(
          title = data.title,
          imageName = data.imageName,
          // Set the tab when the button is clicked 
          onClick = {
//            val activity = fragment.activity as MainActivity
//            activity.bottomNavigationController.selectTab(2)

            val activity = fragment.activity as SignInActivity
//            activity.startActivity(android.content.Intent(this, MainActivity::class.java)) 
            activity.launchMainActivity() 
            replyTo(message.event)
          })
      }
    }
    val layoutParams = Toolbar.LayoutParams(
      ViewGroup.LayoutParams.WRAP_CONTENT,
      ViewGroup.LayoutParams.WRAP_CONTENT
    ).apply { 
      gravity = Gravity.END
      setMargins(0, 0, marginInPx, 0)
    }

    val toolbar = fragment.toolbarForNavigation()
    toolbar?.addView(composeView, layoutParams)
  }

  private fun removeButton() {
    val toolbar = fragment.toolbarForNavigation()
    val button = toolbar?.findViewById<ComposeView>(buttonId)
    toolbar?.removeView(button)
  }

  @Serializable
  data class MessageData(
    val title: String,
    @SerialName("androidImage") val imageName: String?
  )
}

@Composable
private fun ToolbarButton(title: String, imageName: String?, onClick: () -> Unit) {
  Button(
    onClick = onClick,
    colors = ButtonDefaults.buttonColors(
//      containerColor = Color.Transparent,
      containerColor = Color(0xFF0172AD),
//      contentColor = Color.Black,
      contentColor = Color.White,
      disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
      disabledContentColor = Color.White.copy(alpha = 0.5f),
    ),
//    shape = RoundedCornerShape(4.dp),
    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    modifier = Modifier.height(36.dp),
  ) {
    imageName?.let {
      Text(
        text = it,
//        fontFamily = FontFamily(Font(R.font.material_symbols)),
        fontFamily = FontFamily(Font(R.font.material_symbols)),
        fontSize = 28.sp,
//        style = TextStyle(fontFeatureSettings = "liga"),
        style = MaterialTheme.typography.labelLarge
      )
    } ?: Text(title)
  }
}
