plugins {
  id("com.android.application") version "8.7.1"
  id("org.jetbrains.kotlin.android") version "2.1.20"
  id("org.jetbrains.kotlin.plugin.compose")  version "2.1.20"
  id("org.jetbrains.kotlin.plugin.serialization") version "2.1.20"
}

android {
  namespace = "com.matthewblott.scribble"
  compileSdk = 35

  defaultConfig {
    minSdk = 35 
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_18
    targetCompatibility = JavaVersion.VERSION_18
  }

  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_18.toString()
  }

}

repositories {
  google()
  mavenCentral()
}

dependencies {
   implementation("androidx.activity:activity-ktx:1.10.1")
   implementation("androidx.compose:compose-bom:2025.03.00")
   implementation("androidx.core:core-ktx:1.15.0")
   implementation("com.google.android.play:review-ktx:2.0.2")

  // AndroidX
  implementation("androidx.appcompat:appcompat:1.7.0")
  implementation("androidx.browser:browser:1.8.0")
  implementation("androidx.recyclerview:recyclerview:1.4.0")
  implementation("androidx.constraintlayout:constraintlayout:2.2.1")

  implementation("com.google.android.flexbox:flexbox:3.0.0")

  // JSON
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1")

  // Material
  implementation("com.google.android.material:material:1.12.0")

  // Images
  implementation("io.coil-kt:coil:2.7.0")

  // Hotwire
  implementation("dev.hotwire:core:1.2.0")
  implementation("dev.hotwire:navigation-fragments:1.2.0")
  implementation("androidx.compose.material3:material3-android:1.3.2")

}
