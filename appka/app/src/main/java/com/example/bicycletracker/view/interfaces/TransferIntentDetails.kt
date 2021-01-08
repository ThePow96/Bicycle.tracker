package com.example.bicycletracker.view.interfaces

import com.example.bicycletracker.model.PlacesData
import com.example.bicycletracker.model.RoutesData

interface TransferIntentDetails {
    fun moveDetails(item : PlacesData, position: Int)
}

interface TransferRoutesDetails{
    fun moveRoutesDetails(item: RoutesData, position: Int)
}