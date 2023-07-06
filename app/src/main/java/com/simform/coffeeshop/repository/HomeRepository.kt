package com.simform.coffeeshop.repository

import android.util.Log
import com.simform.coffeeshop.data.model.CoffeeModel
import com.simform.coffeeshop.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository(private val apiInterface: ApiInterface) {

    fun getCoffee(callback: (List<CoffeeModel>?) -> Unit) {
        val result = apiInterface.fetchCoffee()
        result.enqueue(object : Callback<List<CoffeeModel>> {
            override fun onResponse(
                call: Call<List<CoffeeModel>>,
                response: Response<List<CoffeeModel>>
            ) {
                callback(response.body())
            }

            override fun onFailure(call: Call<List<CoffeeModel>>, t: Throwable) {
                Log.d("User", "onFailure: ${t.localizedMessage}")
            }

        })
    }

}