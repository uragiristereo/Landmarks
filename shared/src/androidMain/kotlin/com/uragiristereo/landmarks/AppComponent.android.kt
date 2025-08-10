package com.uragiristereo.landmarks

import android.content.Context
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.Provides

@Component
abstract class AndroidAppComponent(
    @get:Provides val context: Context,
) : AppComponent()

