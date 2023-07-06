package com.simform.coffeeshop.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoffeeModel(
    @SerializedName("coffee_type")
    var coffeeType: String?,

    @SerializedName("list")
    var list: ArrayList<CoffeeList>?
) : Parcelable

@Parcelize
data class CoffeeList(
    @SerializedName("coffeeName")
    var coffeeName: String? = null,

    @SerializedName("coffeeTag")
    var coffeeTag: String? = null,

    @SerializedName("price")
    var price: String? = null,

    @SerializedName("rating")
    var rating: Int? = null,

    @SerializedName("image")
    var image: String? = null,

    @SerializedName("id")
    var id: String? = null,

    @SerializedName("coffeeListId")
    var coffeeListId: String? = null
) : Parcelable {
    companion object {
        var cartItemList = ArrayList<CoffeeList>()
        var coffeeTypeList = ArrayList<String>()
    }
}