package com.simform.coffeeshop.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simform.coffeeshop.model.UserRequest
import com.simform.coffeeshop.model.UserResponse
import com.simform.coffeeshop.network.ApiRegisterClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupViewModel : ViewModel() {

    private val registerLiveData = MutableLiveData<UserResponse>()

    val registerResponse: LiveData<UserResponse>
        get() = registerLiveData

    //    "eve.holt@reqres.in"
//    "pistol"
//    email: String = "eve.holt@reqres.in", password: String = "pistol"
    fun registerUser(email: String, password: String) {
        val request =
            ApiRegisterClient.ApiRegisterClient.registerUser(
                UserRequest(
                    "eve.holt@reqres.in",
                    "pistol"
                )
            )

        request.enqueue(object : Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                registerLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                print(t.localizedMessage)
            }
        })

    }

}