package com.example.akakcecasestudy_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.akakcecasestudy_android.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.activity.viewModels
import androidx.lifecycle.Observer


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"
    private lateinit var horizontalAdapter: HorizontalProductAdapter
    private lateinit var gridAdapter: GridProductAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("DEBUG_TAG", "App launched.")
        viewModel.fetchHorizontalProducts()
        viewModel.fetchGridProducts()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.horizontalProducts.observe(this, Observer { products ->
            horizontalAdapter = HorizontalProductAdapter(products) { product ->
                val intent = Intent(this, ProductDetailActivity::class.java)
                intent.putExtra("product_id", product.id)
                startActivity(intent)
            }
            binding.horizontalRecyclerView.adapter = horizontalAdapter
            binding.horizontalRecyclerView.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        })

        viewModel.gridProducts.observe(this, Observer { products ->
            gridAdapter = GridProductAdapter(products) { product ->
                val intent = Intent(this, ProductDetailActivity::class.java)
                intent.putExtra("product_id", product.id)
                startActivity(intent)
            }
            binding.gridRecyclerView.adapter = gridAdapter
            binding.gridRecyclerView.layoutManager =
                GridLayoutManager(this, 2)
        })

        viewModel.error.observe(this, Observer { message ->
            Log.e("MainActivity", "Error: $message")
        })
    }

}
