package com.uragiristereo.landmarks.ui.landmarklist

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.stateIn
import com.uragiristereo.landmarks.data.landmark.Landmark
import com.uragiristereo.landmarks.data.landmark.LandmarkRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import me.tatarka.inject.annotations.Inject

@Inject
class LandmarkListViewModel(repository: LandmarkRepository) : ViewModel() {
    @NativeCoroutinesState
    val landmarks: StateFlow<List<Landmark>> = repository.landmarks
        .stateIn(
            viewModelScope = viewModelScope,
            started = SharingStarted.Companion.WhileSubscribed(),
            initialValue = emptyList(),
        )

    private val _showFavoritesOnly = MutableStateFlow(viewModelScope, false)

    @NativeCoroutinesState
    val showFavoritesOnly: StateFlow<Boolean> = _showFavoritesOnly.asStateFlow()

    @NativeCoroutinesState
    val filteredLandmarks: StateFlow<List<Landmark>> = combine(
        landmarks,
        showFavoritesOnly
    ) { landmarks, showFavoritesOnly ->
        if (showFavoritesOnly) {
            landmarks.filter { it.isFavorite }
        } else {
            landmarks
        }
    }.stateIn(
        viewModelScope = viewModelScope,
        started = SharingStarted.Companion.WhileSubscribed(),
        initialValue = emptyList(),
    )

    fun toggleShowFavoritesOnly() {
        _showFavoritesOnly.value = !_showFavoritesOnly.value
    }
}
