package com.example.wonderwallet

import android.content.Intent
import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wonderwallet.adapter.CategoryAdapter
import com.example.wonderwallet.model.Category
import com.example.wonderwallet.model.WonderWalletDB

class MainActivity : AppCompatActivity() {

    private lateinit var db: WonderWalletDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val grid = findViewById<GridView>(R.id.gridCategories)

        db = WonderWalletDB.getDatabase(this)

        val categoryDao = db.categoryDao()

        //  Load categories from database
        categoryDao.getAllCategories().observe(this) { categories ->

            if (categories.isEmpty()) {
                Toast.makeText(this, "No categories found", Toast.LENGTH_SHORT).show()
            }

            val adapter = CategoryAdapter(this, categories)
            grid.adapter = adapter
        }

        //  Category click handling (IMPORTANT FOR FUNCTIONALITY)
        grid.setOnItemClickListener { _, _, position, _ ->

            val selectedCategory = grid.adapter.getItem(position) as Category

            // Example: navigate to another screen (you can change this)
            val intent = Intent(this, Analysis::class.java).apply {
                putExtra("category_name", selectedCategory.name)
            }

            startActivity(intent)
        }
    }
}