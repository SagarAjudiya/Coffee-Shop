package com.simform.coffeeshop

import androidx.lifecycle.MutableLiveData
import com.simform.coffeeshop.model.CoffeeModel
import com.simform.coffeeshop.network.ApiClient
import com.simform.coffeeshop.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

    private var apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)

    fun fetAllCoffee(): MutableLiveData<List<CoffeeModel>?> {
        val data = MutableLiveData<List<CoffeeModel>?>()

        apiInterface.fetchCoffee().enqueue(object : Callback<List<CoffeeModel>> {
            override fun onResponse(
                call: Call<List<CoffeeModel>>,
                response: Response<List<CoffeeModel>>
            ) {
                val res = response.body()
                if (response.code() == 200 && res != null) {
                    data.value = res
                } else {
                    data.value = null
                }
            }

            override fun onFailure(call: Call<List<CoffeeModel>>, t: Throwable) {
                data.value = null
            }

        })

        return data
    }

}