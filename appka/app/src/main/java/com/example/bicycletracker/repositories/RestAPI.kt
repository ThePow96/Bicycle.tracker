package com.example.bicycletracker.repositories

import com.example.bicycletracker.model.PlacesData
import retrofit2.Call
import retrofit2.http.GET

interface RestAPI {

    @GET("/ThePow96/Bicycle.tracker/master/routesJson.json")
    fun getGamesList(): Call<List<PlacesData>>
}