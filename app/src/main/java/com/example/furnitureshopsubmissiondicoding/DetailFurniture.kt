package com.example.furnitureshopsubmissiondicoding

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.log

class DetailFurniture : AppCompatActivity() {
    private lateinit var toolbarDetailFurniture: Toolbar
    private lateinit var furnitureImage: ImageView
    private lateinit var furnitureName: TextView
    private lateinit var furniturePrice: TextView
    private lateinit var furnitureDescription: TextView
    companion object{
        const val EXTRA_FURNITURE = "extra_furniture"
        private const val STATE_FURNITURE_IMAGE = "state_furniture_image"
        private const val STATE_FURNITURE_NAME = "state_furniture_name"
        private const val STATE_FURNITURE_PRICE = "state_furniture_price"
        private const val STATE_FURNITURE_DESCRIPTION = "state_furniture_description"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_furniture)

        toolbarDetailFurniture = findViewById(R.id.toolbar_detail_furniture)
        setSupportActionBar(toolbarDetailFurniture)

        val furniture = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Furniture>(EXTRA_FURNITURE, Furniture::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Furniture>(EXTRA_FURNITURE)
        }

        furnitureImage = findViewById(R.id.detail_furniture_image)
        furnitureName = findViewById(R.id.detail_furniture_name)
        furniturePrice = findViewById(R.id.detail_furniture_price)
        furnitureDescription = findViewById(R.id.detail_furniture_description)

        furniture?.furnitureImage?.let { furnitureImage.setImageResource(it) }
        furnitureName.text = furniture?.furnitureName
        furniturePrice.text = furniture?.furniturePrice
        furnitureDescription.text = furniture?.furnitureDescription

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.detail_furniture)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if(savedInstanceState != null){
            val resultFurnitureName = savedInstanceState.getString(STATE_FURNITURE_NAME)
            val resultFurniturePrice = savedInstanceState.getString(STATE_FURNITURE_PRICE)
            val resultFurnitureDescription = savedInstanceState.getString(STATE_FURNITURE_DESCRIPTION)

            furnitureName.text = resultFurnitureName
            furniturePrice.text = resultFurniturePrice
            furnitureDescription.text = resultFurnitureDescription
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
//        outState.putInt(STATE_FURNITURE_IMAGE, furnitureImage.let { furnitureImage.setImageResource(it) })
        outState.putString(STATE_FURNITURE_NAME, furnitureName.text.toString())
        outState.putString(STATE_FURNITURE_PRICE, furniturePrice.text.toString())
        outState.putString(STATE_FURNITURE_DESCRIPTION, furnitureDescription.text.toString())
    }
}