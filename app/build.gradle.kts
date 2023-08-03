import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
}

android {
    namespace = "com.ayeminoo.tsuka"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.ayeminoo.tsuka"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            isDebuggable = true
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    buildFeatures {
        buildConfig = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }

    flavorDimensions += "mode"
    productFlavors {
        create("dev") {
            dimension = "mode"
            applicationIdSuffix = ".dev"
            val prop = Properties().apply {
                load(FileInputStream(File(rootProject.rootDir, "local.properties")))
            }
            val appID = prop.getProperty("APP_ID") ?: ""
            buildConfigField("String", "APP_ID", appID)
        }
        create("prod") {
            dimension = "mode"
            val prop = Properties().apply {
                load(FileInputStream(File(rootProject.rootDir, "local.properties")))
            }
            val appID = prop.getProperty("APP_ID") ?: ""
            buildConfigField("String", "APP_ID", appID)
        }
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")

    // Compose
    val composeBom = platform("androidx.compose:compose-bom:2023.06.01")
    implementation(composeBom)

    // Material Design 2
    implementation("androidx.compose.material:material")
    // Android Studio Preview support
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    // Navigation
    implementation("androidx.navigation:navigation-compose:2.6.0")
    // Integration with activities
    implementation("androidx.activity:activity-compose:1.7.2")
    // Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")

    // Hilt
    implementation("com.google.dagger:hilt-android:2.46.1")
    kapt("com.google.dagger:hilt-android-compiler:2.46.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    // Hilt Testing
    kaptTest("com.google.dagger:hilt-android-compiler:2.46.1")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.44")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.46.1")

    // Testing
    androidTestImplementation(composeBom)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    debugImplementation("androidx.compose.ui:ui-tooling")
    androidTestImplementation("androidx.navigation:navigation-testing:2.6.0")

    implementation("com.jakewharton.timber:timber:5.0.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    implementation("io.github.serpro69:kotlin-faker:1.14.0")
    implementation("io.coil-kt:coil-compose:2.4.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
}