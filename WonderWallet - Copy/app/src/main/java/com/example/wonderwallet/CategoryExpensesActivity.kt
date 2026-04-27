package com.example.wonderwallet

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.wonderwallet.adapter.ExpenseAdapter
import com.example.wonderwallet.model.WonderWalletDB

class CategoryExpensesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_expenses)

        val categoryName = intent.getStringExtra("category_name") ?: ""

        val title = findViewById<TextView>(R.id.txtCategoryTitle)
        val list = findViewById<ListView>(R.id.listExpenses)
        val btnAddExpense = findViewById<Button>(R.id.btnAddExpense)

        title.text = categoryName

        // ➕ Navigate to Add Expense
        btnAddExpense.setOnClickListener {
            val intent = Intent(this, AddExpenseActivity::class.java)
            intent.putExtra("category_name", categoryName)
            startActivity(intent)
        }

        // 🧠 ROOM DATABASE CONNECTION
        val db = WonderWalletDB.getDatabase(this)
        val dao = db.expenseDao()

        dao.getAllExpenses().observe(this) { allExpenses ->

            val filteredExpenses = allExpenses.filter { expense ->
                expense.category == categoryName
            }

            val adapter = ExpenseAdapter(this, filteredExpenses)
            list.adapter = adapter
        }
    }
}