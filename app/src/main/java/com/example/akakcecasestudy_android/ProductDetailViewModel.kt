package com.example.akakcecasestudy_android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailViewModel : ViewModel() {

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> get() = _product

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchProductDetail(id: Int) {
        ApiClient.productService.getProductDetail(id).enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                if (response.isSuccessful) {
                    _product.value = response.body()
                } else {
                    _error.value = "Product detail could not launched."
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                _error.value = t.message ?: "Error emerged."
            }
        })
    }
}
