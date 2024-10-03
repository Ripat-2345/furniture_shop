package com.example.furnitureshopsubmissiondicoding

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.furnitureshopsubmissiondicoding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var listFurniture: RecyclerView
    private val list = ArrayList<Furniture>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listFurniture = binding.listFurniture
        listFurniture.setHasFixedSize(true)

        list.addAll(getListFurniture())
        showRecyclerList()

        setSupportActionBar(binding.toolbar)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_about -> {
                val aboutMeIntent = Intent(this@MainActivity, AboutMe::class.java)
                startActivity(aboutMeIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListFurniture(): ArrayList<Furniture>{
        val dataName = resources.getStringArray(R.array.data_furniture_name)
        val dataPrice = resources.getStringArray(R.array.data_furniture_price)
        val dataDescription = resources.getStringArray(R.array.data_furniture_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_furniture_image)
        val listFurniture = ArrayList<Furniture>()
        for (i in dataName.indices){
            val furniture = Furniture(dataName[i], dataPrice[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listFurniture.add(furniture)
        }

        return listFurniture
    }

    private fun showRecyclerList(){
        listFurniture.layoutManager = LinearLayoutManager(this)
        val listFurnitureAdapter = ListFurnitureAdapter(list)
        listFurniture.adapter = listFurnitureAdapter

        listFurnitureAdapter.setOnItemClickCallback(object: ListFurnitureAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Furniture){
                // intent navigate to detail page
                val detailFurnitureIntent = Intent(this@MainActivity, DetailFurniture::class.java)
                detailFurnitureIntent.putExtra(DetailFurniture.EXTRA_FURNITURE, data)
                startActivity(detailFurnitureIntent)
            }
        })
    }
}