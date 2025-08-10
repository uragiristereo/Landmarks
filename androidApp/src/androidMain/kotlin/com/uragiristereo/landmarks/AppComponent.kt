package com.uragiristereo.landmarks

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext

val LocalAppComponent = staticCompositionLocalOf<AndroidAppComponent> {
    error("No AppComponent provided")
}

@Composable
fun ProvideAppComponent(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val application = context.applicationContext as LandmarksApp

    CompositionLocalProvider(
        value = LocalAppComponent provides application.appComponent,
        content = content,
    )
}
