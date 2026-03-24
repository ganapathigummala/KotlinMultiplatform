import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.nativecoroutines)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    sourceSets {

        androidMain.dependencies {
            implementation(libs.core.splashscreen)

            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.compose.runtime)
            implementation(libs.compose.ui)
            implementation(libs.compose.material3)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.core)
            implementation(libs.kmp.observableviewmodel.core)
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)

                implementation("com.jakewharton.timber:timber:5.0.1")

            implementation(projects.shared)
            implementation(projects.coreNetwork)


        }

        commonMain.dependencies {

            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)

            implementation(libs.compose.components.resources)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.koin.compose)
        }
    }
}

android {
    namespace = "com.example.multimodule"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        applicationId = "com.example.multimodule"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()

        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {

        debug {
            buildConfigField("Boolean", "ENABLE_LOGS", "true")
        }

        release {
            buildConfigField("Boolean", "ENABLE_LOGS", "false")
            isMinifyEnabled = false
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(libs.compose.uiTooling)
}

