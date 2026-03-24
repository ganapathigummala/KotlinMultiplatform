import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.composeCompiler)   // required for @Composable functions to work

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
            implementation("com.jakewharton.timber:timber:5.0.1")
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.activity.compose)

            implementation(libs.compose.runtime)
            implementation(libs.compose.ui)
            implementation(libs.compose.material3)

            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.koin.compose.viewmodel)
            implementation(libs.ktor.client.android)


            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)

        }

        commonMain.dependencies {

            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.kotlinx.serialization.core)
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.coil.network.ktor)

            implementation(libs.compose.components.resources)

            implementation(libs.koin.compose)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)

        }
    }
}

android {
    namespace = "com.example.multimodule.coreNetwork"
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
