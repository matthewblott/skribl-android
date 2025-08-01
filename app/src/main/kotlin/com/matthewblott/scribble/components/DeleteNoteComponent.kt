package com.matthewblott.scribble.components

import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.webkit.CookieManager
import androidx.appcompat.app.AlertDialog
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
import com.matthewblott.scribble.components.SignInComponent.MessageData
import dev.hotwire.core.bridge.BridgeComponent
import dev.hotwire.core.bridge.BridgeDelegate
import dev.hotwire.core.bridge.Message
import dev.hotwire.navigation.destinations.HotwireDestination
import dev.hotwire.navigation.fragments.HotwireFragment
import kotlinx.serialization.Serializable

class DeleteNoteComponent (
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
//      "show" -> showAlert(message)
      else -> Log.w("DeleteNoteComponent", "Unknown event for message: $message")
    }
  }

  private fun showAlert(message: Message) {
    val data = message.data<MessageData>() ?: return

    AlertDialog.Builder(fragment.requireContext()).setTitle(data.title)
      .setMessage(data.description).setCancelable(true)
      .setNegativeButton(data.dismiss, null)
      .setPositiveButton(data.confirm) { _, _ ->
        replyTo(message.event)
      }.show()
  }

  private fun addButton(message: Message) {
    val data = message.data<com.matthewblott.scribble.components.SignInComponent.MessageData>() ?: return
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
          onClick = {
            showAlert(message) 
//            replyTo(message.event)
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
  private data class MessageData(
    val title: String,
    val description: String?,
    val destructive: Boolean,
    val confirm: String,
    val dismiss: String
  )
}

@Composable
private fun ToolbarButton(title: String, imageName: String?, onClick: () -> Unit) {
  Button(
    onClick = onClick,
    colors = ButtonDefaults.buttonColors(
      containerColor = Color(0xFF0172AD),
      contentColor = Color.White,
      disabledContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
      disabledContentColor = Color.White.copy(alpha = 0.5f),
    ),
    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    modifier = Modifier.height(36.dp),
  ) {
    imageName?.let {
      Text(
        text = it,
        fontFamily = FontFamily(Font(R.font.material_symbols)),
        fontSize = 28.sp,
        style = MaterialTheme.typography.labelLarge
      )
    } ?: Text(title)
  }
}
