package com.example.wonderwallet

import android.content.Intent
import android.os.Bundle
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.example.wonderwallet.adapter.CategoryAdapter
import com.example.wonderwallet.model.Category
import com.example.wonderwallet.model.WonderWalletDB
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private lateinit var db: WonderWalletDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val grid = findViewById<GridView>(R.id.gridCategories)

        db = WonderWalletDB.getDatabase(this)

        val categoryDao = db.categoryDao()

        // 🟢 LOAD CATEGORIES FROM ROOM
        categoryDao.getAllCategories().observe(this) { categories ->

            if (categories.isEmpty()) {
                Toast.makeText(this, "No categories found", Toast.LENGTH_SHORT).show()
            }

            val adapter = CategoryAdapter(this, categories)
            grid.adapter = adapter

            // 🟢 CLICK HANDLING (INSIDE OBSERVER FOR SAFETY)
            grid.setOnItemClickListener { _, _, position, _ ->

                val selectedCategory = categories[position]

                val intent = Intent(this, CategoryExpensesActivity::class.java)
                intent.putExtra("category_name", selectedCategory.name)

                startActivity(intent)
            }
        }
    }
}