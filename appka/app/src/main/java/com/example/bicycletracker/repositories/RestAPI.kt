package com.example.bicycletracker.repositories

import com.example.bicycletracker.model.PlacesData
import com.example.bicycletracker.model.RoutesData
import retrofit2.Call
import retrofit2.http.GET

interface RestAPI {

    @GET("/ThePow96/Bicycle.tracker/master/routesJson.json")
    fun getGamesList(): Call<List<PlacesData>>

    @GET("/ThePow96/Bicycle.tracker/master/places.json")
    fun getPlacesList(): Call<List<PlacesData>>

    @GET("/ThePow96/Bicycle.tracker/master/routesLinks.json")
    fun getRoutesList(): Call<List<RoutesData>>
}