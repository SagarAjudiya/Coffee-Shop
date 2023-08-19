package com.simform.coffeeshop.network

import com.simform.coffeeshop.data.model.UserRequest
import com.simform.coffeeshop.data.model.UserSignupResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiRegisterInterface {

    @POST("register")
    fun registerUser(@Body userRequest: UserRequest): Call<UserSignupResponse>
}