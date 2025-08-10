pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("com.android.settings") version ("8.12.0")
}

android {
    compileSdk = 36
    minSdk = 21
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Landmarks"

include(":androidApp")
include(":shared")
