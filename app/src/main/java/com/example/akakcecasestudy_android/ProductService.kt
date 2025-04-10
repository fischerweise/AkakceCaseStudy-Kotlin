package com.example.akakcecasestudy_android

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {

    @GET("products")
    fun getAllProducts(): Call<List<Product>>

    @GET("products")
    fun getLimitedProducts(@Query("limit") limit: Int): Call<List<Product>>

    @GET("products/{id}")
    fun getProductDetail(@Path("id") id: Int): Call<Product>
}