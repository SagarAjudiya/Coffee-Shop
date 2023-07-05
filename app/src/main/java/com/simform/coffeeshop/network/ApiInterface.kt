package com.simform.coffeeshop.network

import com.simform.coffeeshop.model.CoffeeModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("coffeeList")
    suspend fun fetchCoffee(): Response<List<CoffeeModel>>
}