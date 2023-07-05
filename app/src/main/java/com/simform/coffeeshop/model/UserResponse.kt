package com.simform.coffeeshop.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("token")
    val token: String
)
