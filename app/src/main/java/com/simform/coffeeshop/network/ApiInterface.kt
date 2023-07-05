package com.simform.coffeeshop.network

import com.simform.coffeeshop.model.CoffeeModel
import retrofit2.Call
import retrofit2.http.POST

interface ApiInterface {

    @POST("coffeeList")
    fun fetchCoffee(): Call<List<CoffeeModel>>
}