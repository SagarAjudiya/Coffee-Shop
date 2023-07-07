package com.simform.coffeeshop.data.model

import com.google.gson.annotations.SerializedName

data class UserSignupResponse(
    @SerializedName("id")
    val id: String,

    @SerializedName("token")
    val token: String
)
