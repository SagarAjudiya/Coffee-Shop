package com.simform.coffeeshop.network

import com.simform.coffeeshop.data.model.CoffeeModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("coffeeList")
    fun fetchCoffee(): Call<List<CoffeeModel>>
}