package com.uragiristereo.landmarks.ui.landmarkdetail

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.stateIn
import com.uragiristereo.landmarks.data.landmark.Landmark
import com.uragiristereo.landmarks.data.landmark.LandmarkRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import me.tatarka.inject.annotations.Assisted
import me.tatarka.inject.annotations.Inject

@Inject
class LandmarkDetailViewModel(
    @Assisted private val landmarkId: Int,
    private val repository: LandmarkRepository,
) : ViewModel() {
    @NativeCoroutinesState
    val landmark: StateFlow<Landmark?> = repository.landmarks.map { landmarks ->
        landmarks.first { landmark -> landmark.id == landmarkId }
    }.stateIn(
        viewModelScope,
        started = SharingStarted.Companion.WhileSubscribed(),
        initialValue = repository.landmarks.value.first { landmark -> landmark.id == landmarkId },
    )

    fun toggleFavorite() {
        repository.toggleFavorite(landmarkId)
    }
}
