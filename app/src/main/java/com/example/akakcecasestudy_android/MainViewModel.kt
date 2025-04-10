package com.example.akakcecasestudy_android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _horizontalProducts = MutableLiveData<List<Product>>()
    val horizontalProducts: LiveData<List<Product>> = _horizontalProducts

    private val _gridProducts = MutableLiveData<List<Product>>()
    val gridProducts: LiveData<List<Product>> = _gridProducts

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchHorizontalProducts() {
        ApiClient.productService.getLimitedProducts(5).enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful) {
                    _horizontalProducts.value = response.body()
                } else {
                    _error.value = "Horizontal products failed."
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                _error.value = t.message ?: "Error emerged."
            }
        })
    }

    fun fetchGridProducts() {
        ApiClient.productService.getAllProducts().enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful) {
                    _gridProducts.value = response.body()
                } else {
                    _error.value = "Vertical products failed."
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                _error.value = t.message ?: "Error emerged."
            }
        })
    }
}
