package com.example.bicycletracker.model

import java.io.Serializable

data class PlacesData(
    val name: String?,
    val description: String?,
    val longitude: Double,
    val latitude: Double,
    var checkStatus: Boolean = false
) : Serializable
