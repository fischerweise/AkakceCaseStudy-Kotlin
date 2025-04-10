package com.example.akakcecasestudy_android

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.activity.viewModels
import androidx.lifecycle.Observer

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var titleText: TextView
    private lateinit var priceText: TextView
    private lateinit var descriptionText: TextView
    private lateinit var ratingText: TextView
    private val viewModel: ProductDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        imageView = findViewById(R.id.imageView)
        titleText = findViewById(R.id.titleText)
        priceText = findViewById(R.id.priceText)
        descriptionText = findViewById(R.id.descriptionText)
        ratingText = findViewById(R.id.ratingText)

        val productId = intent.getIntExtra("product_id", -1)
        if (productId != -1) {
            viewModel.fetchProductDetail(productId)
            observeViewModel()
        }
    }

    private fun observeViewModel() {
        viewModel.product.observe(this, Observer { product ->
            titleText.text = product.title
            priceText.text = "$${product.price}"
            descriptionText.text = product.description
            ratingText.text = "Rating: ${product.rating.rate} (${product.rating.count} ❤️)️"
            Glide.with(this)
                .load(product.image)
                .into(imageView)
        })

        viewModel.error.observe(this, Observer { message ->
            Log.e("Detail", "Error: $message")
        })
    }
}