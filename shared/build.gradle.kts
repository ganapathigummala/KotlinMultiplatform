import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.nativecoroutines)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    

        iosX64()
        iosArm64()
        iosSimulatorArm64()

    
    sourceSets {
        all {
            languageSettings {
                optIn("kotlin.experimental.ExperimentalObjCName")
                optIn("kotlinx.cinterop.ExperimentalForeignApi")
            }
        }
        androidMain.dependencies {
            implementation("androidx.core:core-splashscreen:1.0.1")
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.compose.runtime)
            implementation(libs.compose.ui)
            implementation(libs.compose.material3)
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)
            implementation("com.jakewharton.timber:timber:5.0.1")

        }
        commonMain.dependencies {
            implementation(projects.coreNetwork)

            implementation(libs.koin.core)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.kmp.observableviewmodel.core)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.example.multimodule.shared"
    buildFeatures {
        buildConfig = true
    }
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
