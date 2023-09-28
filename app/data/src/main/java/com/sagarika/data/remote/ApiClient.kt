package com.sagarika.data.remote

import com.example.e_commerceappwithjetpackcompose.data.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        fun getService(): ProductService {
            return Retrofit.Builder()
                .baseUrl(Constants.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ProductService::class.java)
        }
    }
}