package com.example.wonderwallet

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.GridView
import com.example.wonderwallet.adapter.CategoryAdapter
import com.example.wonderwallet.model.Category
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val grid = findViewById<GridView>(R.id.gridCategories)

        val categories = listOf(
            Category(1, "Food"),
            Category(2, "Transport"),
            Category(3, "Medicine"),
            Category(4, "Groceries"),
            Category(5, "Rent"),
            Category(6, "Gifts"),
            Category(7, "Savings"),
            Category(8, "Entertainment"),
            Category(9, "More")
        )

        val adapter = CategoryAdapter(this, categories)
        grid.adapter = adapter
    }

}