package com.simform.coffeeshop.network

import com.simform.coffeeshop.data.model.CoffeeModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("07d964b5-9fcd-4c0b-a9e2-d539fec05a5e")
    fun fetchCoffee(): Call<ArrayList<CoffeeModel>>

    @GET("07d964b5-9fcd-4c0b-a9e2-d539fec05a5e")
    fun fetchJsonData(): Call<ResponseBody>
}