package com.simform.coffeeshop.repository

import android.util.Log
import com.simform.coffeeshop.data.model.CoffeeModel
import com.simform.coffeeshop.network.ApiClient
import com.simform.coffeeshop.network.ApiInterface
import okhttp3.ResponseBody
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository(private val apiInterface: ApiInterface) {

    fun getCoffee(callback: (List<CoffeeModel>?) -> Unit) {
        val result = apiInterface.fetchCoffee()
        result.enqueue(object : Callback<List<CoffeeModel>> {
            override fun onResponse(
                call: Call<List<CoffeeModel>>, response: Response<List<CoffeeModel>>
            ) {
                callback(response.body())
            }

            override fun onFailure(call: Call<List<CoffeeModel>>, t: Throwable) {
                Log.d("User", "onFailure: ${t.localizedMessage}")
            }
        })
    }

    fun getData() {
        val res = ApiClient.retrofit
        val apiInterface = res.create(ApiInterface::class.java)
        val call = apiInterface.fetchJsonData()

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                val myData = response.body()?.string()
                Log.d("Json", "onResponse: $response")
                val coffeeTypes = myData?.let { parseJsonData(it) }

                if (coffeeTypes != null) {
                    for (coffeeType in coffeeTypes) {
                        Log.d("TAG", "Coffee Type: ${coffeeType.coffeeType}")
                        Log.d("TAG", "ID: ${coffeeType.id}")
                        Log.d("TAG", "List: ")

                        for (coffee in coffeeType.list) {
                            Log.d("TAG", "Coffee Name: ${coffee.coffeeName}")
                            Log.d("TAG", "Coffee Tag: ${coffee.coffeeTag}")
                            Log.d("TAG", "Price: ${coffee.price}")
                            Log.d("TAG", "Rating: ${coffee.rating}")
                            Log.d("TAG", "Image: ${coffee.image}")
                            Log.d("TAG", "ID: ${coffee.id}")
                            Log.d("TAG", "Coffee List ID: ${coffee.coffeeListId}")
                        }
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("TAG", "onFailure: Fail")
            }
        })
    }

    fun parseJsonData(jsonString: String): List<CoffeeType> {
        val coffeeTypes = mutableListOf<CoffeeType>()

        val jsonArray = JSONArray(jsonString)
        for (i in 0 until jsonArray.length()) {
            val jsonCoffeeType = jsonArray.getJSONObject(i)

            val coffeeType = CoffeeType(
                coffeeType = jsonCoffeeType.getString("coffee_type"),
                id = jsonCoffeeType.getString("id"),
                list = parseCoffeeList(jsonCoffeeType.getJSONArray("list"))
            )

            coffeeTypes.add(coffeeType)
        }

        return coffeeTypes
    }

    private fun parseCoffeeList(jsonArray: JSONArray): List<Coffee> {
        val coffeeList = mutableListOf<Coffee>()

        for (i in 0 until jsonArray.length()) {
            val jsonCoffee = jsonArray.getJSONObject(i)

            val coffee = Coffee(
                coffeeName = jsonCoffee.getString("coffeeName"),
                coffeeTag = jsonCoffee.getString("coffeeTag"),
                price = jsonCoffee.getString("price"),
                rating = jsonCoffee.getInt("rating"),
                image = jsonCoffee.getString("image"),
                id = jsonCoffee.getString("id"),
                coffeeListId = jsonCoffee.getString("coffeeListId")
            )
            coffeeList.add(coffee)
        }
        return coffeeList
    }

}

data class CoffeeType(
    val coffeeType: String,
    val id: String,
    val list: List<Coffee>,
)

data class Coffee(
    val coffeeName: String,
    val coffeeTag: String,
    val price: String,
    val rating: Int,
    val image: String,
    val id: String,
    val coffeeListId: String,
)