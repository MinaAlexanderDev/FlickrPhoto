import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.com.google.dagger.hilt.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
//    kotlin("kapt")
    alias(libs.plugins.devtools.ksp)
    id("kotlin-parcelize")

}
val apiKeyPropertiesFile = rootProject.file("local.properties")
val apiKeyProperties = Properties()
apiKeyProperties.load(FileInputStream(apiKeyPropertiesFile))
android {
    namespace = "com.example.moviephoto"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.moviephoto"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "API_KEY", apiKeyProperties.getProperty("API_KEY"))

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true

    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
    packaging {
        resources.excludes.apply {
            add("/META-INF/{AL2.0,LGPL2.1}")
            add("META-INF/DEPENDENCIES")
            add("META-INF/LICENSE")
            add("META-INF/LICENSE.txt")
            add("META-INF/license.txt")
            add("META-INF/NOTICE")
            add("META-INF/NOTICE.txt")
            add("META-INF/notice.txt")
            add("META-INF/ASL2.0")
            add("META-INF/*")
            add("META-INF/*.kotlin_module")
            add("project.properties")
            add("META-INF/INDEX.LIST")
        }
    }

}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.androidx.junit.ktx)
    implementation(libs.core)
    testImplementation(libs.junit)
//    testImplementation(libs.android.test.support.runner)
//    testImplementation(libs.android.test.support.rules)
    testImplementation(libs.robolectric)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockk)
    androidTestImplementation(libs.mockito.core)
    androidTestImplementation(libs.mockito.kotlin)
    androidTestImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.android.arch.test)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(project(mapOf("path" to ":app")))
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp3)
    implementation(libs.logging.interceptor)
    implementation(libs.timber)
    implementation(libs.coil)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)
    implementation(libs.paging)
    implementation(libs.paging.run.time)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.viewmodel.compose)
    implementation(libs.navigation.compose)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)
    testImplementation(libs.turbine)
    androidTestImplementation(libs.turbine)
    implementation(libs.paging.common)
    testImplementation(libs.hilt.android.testing)
    implementation(libs.material)
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    ksp(libs.room.compiler)
    testImplementation(libs.room.test)
    implementation(libs.android.arch.persistence.runtime)
    ksp(libs.android.arch.persistence)
    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation(libs.androidx.rules)
    androidTestImplementation(libs.androidx.core)
    androidTestImplementation(libs.androidx.core.ktx)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.junit.ktx)
    androidTestImplementation(libs.androidx.truth)
    androidTestUtil(libs.androidx.orchestrator)

}
//val compileKotlin: KotlinCompile by tasks
//compileKotlin.kotlinOptions {
//    languageVersion = "1.9"
//}