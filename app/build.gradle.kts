// plugins {
//   alias(libs.plugins.android.application)
//   alias(libs.plugins.kotlin.serialization) apply false
//   alias(libs.plugins.kotlin.compose)
// }
//
// android {
//   namespace = "com.matthewblott.skribl"
//   compileSdk {
//     version = release(37)
//   }
//
//   defaultConfig {
//     applicationId = "com.matthewblott.skribl"
//     minSdk = 28
//     targetSdk = 37
//     versionCode = 1
//     versionName = "1.0"
//
//     testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//   }
//
//   buildTypes {
//     release {
//       optimization {
//         enable = false
//       }
//     }
//   }
//   compileOptions {
//     sourceCompatibility = JavaVersion.VERSION_17
//     targetCompatibility = JavaVersion.VERSION_17
//   }
//   buildFeatures {
//     compose = true
//   }
// }
//
// dependencies {
//   implementation(platform(libs.androidx.compose.bom))
//   implementation(libs.androidx.activity.compose)
//   implementation(libs.androidx.compose.material3)
//   implementation(libs.androidx.compose.ui)
//   implementation(libs.androidx.compose.ui.graphics)
//   implementation(libs.androidx.compose.ui.tooling.preview)
//   implementation(libs.androidx.core.ktx)
//   implementation(libs.androidx.lifecycle.runtime.ktx)
//   implementation("dev.hotwire:core:1.3.1")
//   implementation("dev.hotwire:navigation-fragments:1.3.1")
//   implementation("com.github.joemasilotti:bridge-components:0.14.0")
//   implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1")
//   implementation(platform("androidx.compose:compose-bom:2026.06.00"))
//   testImplementation(libs.junit)
//   androidTestImplementation(platform(libs.androidx.compose.bom))
//   androidTestImplementation(libs.androidx.compose.ui.test.junit4)
//   androidTestImplementation(libs.androidx.espresso.core)
//   androidTestImplementation(libs.androidx.junit)
//   debugImplementation(libs.androidx.compose.ui.test.manifest)
//   debugImplementation(libs.androidx.compose.ui.tooling)
// }

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.serialization)
  alias(libs.plugins.compose.compiler)
}

android {
  namespace = "com.matthewblott.skribl"
  compileSdk {
    version = release(37)
  }

  defaultConfig {
    applicationId = "com.matthewblott.skribl"
    minSdk = 28
    targetSdk = 37
    versionCode = 1
    versionName = "1.0"
  }
  buildFeatures {
    compose = true
  }
  buildTypes {
    release {
      isMinifyEnabled = true
      isShrinkResources = true
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
      ndk {
        debugSymbolLevel = "FULL"
      }
      optimization {
        enable = false
      }
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
  }
}

dependencies {
  implementation(libs.androidx.activity.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.constraintlayout)
  implementation(libs.androidx.core.ktx)
  implementation(libs.material)
  implementation("dev.hotwire:core:1.3.1")
  implementation("dev.hotwire:navigation-fragments:1.3.1")
  implementation("com.github.joemasilotti:bridge-components:0.14.0")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.1")

  implementation(platform("androidx.compose:compose-bom:2026.06.00"))

  implementation("androidx.compose.material3:material3")
  implementation("androidx.compose.ui:ui")
  implementation("androidx.compose.ui:ui-tooling-preview")
  implementation("androidx.compose.runtime:runtime")
}

