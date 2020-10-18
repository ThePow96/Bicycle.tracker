package com.example.bicycletracker.repositories

import android.util.Log
import com.example.bicycletracker.model.PlacesData
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

private const val RETROFIT_LOG = "Retrofit: "
private const val RETROFIT_NETWORK_ERROR_MSG = "Network error"
private const val BASE_URL = "https://raw.githubusercontent.com"
class NetworkLoading {
    private lateinit var restAPI: RestAPI
    private var gamesList = listOf<PlacesData>()

    fun loadHttpData(): List<PlacesData> {
        val gson = GsonBuilder().setLenient().create()
        val okHttpHandler = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL).client(okHttpHandler).build()

        restAPI = retrofit.create(RestAPI::class.java)
        gamesLoadingResponse()
        return gamesList
    }

    private fun gamesLoadingResponse(){
        try {
            val restResponse = restAPI.getGamesList().execute()
            if (restResponse.isSuccessful){
                gamesList = restResponse.body()
            }else{
                gamesList = emptyList()
                Log.w(RETROFIT_LOG, RETROFIT_NETWORK_ERROR_MSG)
            }
        }catch (e: IOException){
            Log.w(RETROFIT_LOG, e.message.toString())
        }
    }
}
