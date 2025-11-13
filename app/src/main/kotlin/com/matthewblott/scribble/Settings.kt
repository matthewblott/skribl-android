package com.matthewblott.scribble

object Settings {
  var userId = 0
  val current: Environment = Environment.Local

  enum class Environment(val url: String) {
    Remote("https://scribble.coderscoffeehouse.com"),
    Local("http://10.0.2.2:3000")
  }
  
}