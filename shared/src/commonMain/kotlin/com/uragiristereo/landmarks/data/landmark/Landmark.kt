package com.uragiristereo.landmarks.data.landmark

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Landmark(
    val id: Int,
    val name: String,
    val park: String,
    val state: String,
    @SerialName("description")
    val desc: String,
    val isFavorite: Boolean,
    val imageName: String,
    val coordinates: Coordinates,
) {
    @Serializable
    data class Coordinates(
        val longitude: Double,
        val latitude: Double,
    )
}
