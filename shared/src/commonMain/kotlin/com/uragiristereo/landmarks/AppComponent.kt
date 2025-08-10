package com.uragiristereo.landmarks

import com.uragiristereo.landmarks.data.landmark.LandmarkRepository
import com.uragiristereo.landmarks.data.landmark.RealLandmarkRepository
import com.uragiristereo.landmarks.ui.landmarkdetail.LandmarkDetailViewModel
import com.uragiristereo.landmarks.ui.landmarklist.LandmarkListViewModel
import me.tatarka.inject.annotations.Component
import me.tatarka.inject.annotations.KmpComponentCreate
import me.tatarka.inject.annotations.Provides

@Component
abstract class AppComponent {
    val RealLandmarkRepository.bind: LandmarkRepository; @Provides get() = this

    abstract val landmarkListViewModel: LandmarkListViewModel

    abstract val landmarkDetailViewModel: (Int) -> LandmarkDetailViewModel
}

@Suppress("KotlinNoActualForExpect")
@KmpComponentCreate
expect fun createAppComponent(): AppComponent
