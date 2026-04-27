package com.example.wonderwallet

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import android.widget.ListView
import com.example.wonderwallet.model.Expense
import com.example.wonderwallet.adapter.ExpenseAdapter

class CategoryExpensesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_expenses)

        val categoryName = intent.getStringExtra("category_name")

        val title = findViewById<TextView>(R.id.txtCategoryTitle)
        val list = findViewById<ListView>(R.id.listExpenses)

        title.text = categoryName

        val expenses = listOf(
            Expense("Dinner", "-R453.00", "April 30"),
            Expense("Pizza", "-R410.00", "April 24"),
            Expense("Lunch", "-R115.00", "April 15")
        )

        val adapter = ExpenseAdapter(this, expenses)
        list.adapter = adapter
    }
}
