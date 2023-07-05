package com.simform.coffeeshop.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASEURL = "https://64a41473c3b509573b570614.mockapi.io/api/v1"

class ApiClient {

    companion object {
        lateinit var retrofit: Retrofit

        fun getApiClient(): Retrofit {
            val gson = GsonBuilder().setLenient().create()
            val okHttpClient = OkHttpClient.Builder().build()

            retrofit = Retrofit.Builder()
                .baseUrl(BASEURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit
        }

    }

}