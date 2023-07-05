package com.simform.coffeeshop.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.simform.coffeeshop.model.CoffeeModel
import com.simform.coffeeshop.network.ApiInterface

class Repository(private val apiInterface: ApiInterface) {

    private val coffeeLiveData = MutableLiveData<List<CoffeeModel>>()
//    lateinit var callback: (CoffeeModel) -> Unit

    val coffee: LiveData<List<CoffeeModel>>
        get() = coffeeLiveData

    suspend fun getCoffee() {
        val result = apiInterface.fetchCoffee()
        if (result.body() != null) {
            coffeeLiveData.postValue(result.body())
//            callback(result.body()!!)
        }
    }

}