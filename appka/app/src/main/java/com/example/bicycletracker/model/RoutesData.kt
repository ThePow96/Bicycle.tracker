package com.example.bicycletracker.model

import java.io.Serializable

data class RoutesData(
    val name: String?,
    val description: String?,
    val route : String?,
    val level: String?
) : Serializable