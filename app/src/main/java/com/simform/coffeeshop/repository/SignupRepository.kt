package com.simform.coffeeshop.repository

import android.util.Log
import com.simform.coffeeshop.data.model.UserRequest
import com.simform.coffeeshop.data.model.UserResponse
import com.simform.coffeeshop.network.ApiRegisterClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupRepository {

    fun registerUser(email: String, password: String, callback: (UserResponse) -> Unit) {
        val request = ApiRegisterClient.ApiRegisterClient.registerUser(UserRequest(email, password))

        request.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>, response: Response<UserResponse>
            ) {
                response.body()?.let { callback(it) }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d("User", "onFailure: ${t.localizedMessage}")
            }
        })

    }
}