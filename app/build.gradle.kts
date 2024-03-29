@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.sampleapp"
    compileSdk = 34

    dataBinding {
        this.enable = true
    }

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.example.sampleapp"
        minSdk = 33
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.retrofit)
    implementation(libs.retrofitconverter)
    implementation(libs.okhttp)
    implementation(libs.recyclerview)
    implementation(libs.coil)
    implementation(libs.lifecycle.extension)
    implementation(libs.lifecycle.runtime)
    implementation(libs.lifecycle.extension)
    implementation(libs.lifecycle.livedata)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.activity.version)
    implementation(libs.fragment.version)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.fragment.ui.ktx)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
    implementation(libs.coroutines.services)
    implementation(libs.hilt.android)
    kapt(libs.dagger.hilt.android.compiler)

    // Testing
    implementation(libs.androidx.espresso.contrib)
    implementation(libs.androidx.navigation.testing)
    androidTestImplementation(libs.androidx.navigation.testing)
    implementation(libs.androidx.fragment.testing)
    kaptTest(libs.dagger.hilt.android.compiler)
    androidTestImplementation(libs.hilt.test)
    kaptAndroidTest(libs.dagger.hilt.android.compiler)
    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    testImplementation(libs.mockito.kotlin)
    androidTestImplementation(libs.mockito.android)
    testImplementation(libs.mockk)
    testImplementation(libs.arch.core.test)
    androidTestImplementation(libs.arch.core.test)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.gson.test)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
kapt {
    correctErrorTypes = true
}
