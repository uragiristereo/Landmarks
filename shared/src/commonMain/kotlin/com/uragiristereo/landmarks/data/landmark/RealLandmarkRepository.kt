package com.uragiristereo.landmarks.data.landmark

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import me.tatarka.inject.annotations.Inject

@Inject
class RealLandmarkRepository : LandmarkRepository {
    private val _landmarks = MutableStateFlow(landmarkData)

    @NativeCoroutines
    override val landmarks: StateFlow<List<Landmark>> = _landmarks.asStateFlow()

    override fun toggleFavorite(landmarkId: Int) {
        val newData = landmarks.value.map { landmark ->
            if (landmark.id == landmarkId) {
                landmark.copy(isFavorite = !landmark.isFavorite)
            } else {
                landmark
            }
        }

        _landmarks.value = newData
    }
}
