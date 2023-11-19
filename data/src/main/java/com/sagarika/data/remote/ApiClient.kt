package com.sagarika.data.remote

import com.sagarika.common.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        fun getService(): ProductService {
            return Retrofit.Builder()
                .baseUrl(com.sagarika.common.util.Constants.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ProductService::class.java)
        }
    }
}