package com.uragiristereo.landmarks.data.landmark

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.StateFlow

interface LandmarkRepository {
    @NativeCoroutines
    val landmarks: StateFlow<List<Landmark>>

    fun toggleFavorite(landmarkId: Int)
}
