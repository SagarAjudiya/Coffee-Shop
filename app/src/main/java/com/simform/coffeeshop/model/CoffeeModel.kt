package com.simform.coffeeshop.model

import com.google.gson.annotations.SerializedName

data class CoffeeModel(
    @SerializedName("coffee_type") var coffeeType : String?,
    @SerializedName("list") var list : ArrayList<CoffeeModel>?
)

