import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.nativeCoroutines)
    alias(libs.plugins.ksp)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64() to true,
        iosArm64() to true,
        iosSimulatorArm64() to false,
    ).forEach { (iosTarget, static) ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = static
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.serializationJson)
            implementation(libs.observableViewModel)
            implementation(libs.kotlinInjectRuntime)
        }

        listOf(
            iosX64Main,
            iosArm64Main,
            iosSimulatorArm64Main,
        ).forEach { iosSourceSet ->
            iosSourceSet.languageSettings {
                optIn("kotlinx.cinterop.ExperimentalForeignApi")
                optIn("kotlin.experimental.ExperimentalObjCName")
            }
        }
    }
}

android {
    namespace = "com.uragiristereo.landmarks.shared"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    add("kspCommonMainMetadata", libs.kotlinInjectCompiler)
    add("kspAndroid", libs.kotlinInjectCompiler)
    add("kspIosX64", libs.kotlinInjectCompiler)
    add("kspIosArm64", libs.kotlinInjectCompiler)
    add("kspIosSimulatorArm64", libs.kotlinInjectCompiler)
}
