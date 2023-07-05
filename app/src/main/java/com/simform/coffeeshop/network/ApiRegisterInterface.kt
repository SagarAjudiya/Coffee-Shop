package com.simform.coffeeshop.network

import com.simform.coffeeshop.model.UserRequest
import com.simform.coffeeshop.model.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiRegisterInterface {

    @POST("register")
    fun registerUser(@Body userRequest: UserRequest): Call<UserResponse>
}