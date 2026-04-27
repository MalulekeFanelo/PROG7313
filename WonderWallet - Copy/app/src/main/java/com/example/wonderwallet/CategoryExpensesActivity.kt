package com.example.wonderwallet

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.wonderwallet.adapter.ExpenseAdapter
import com.example.wonderwallet.model.Expense
import com.example.wonderwallet.model.WonderWalletDB

class CategoryExpensesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_expenses)

        val categoryName = intent.getStringExtra("category_name")

        val title = findViewById<TextView>(R.id.txtCategoryTitle)
        val list = findViewById<ListView>(R.id.listExpenses)
        val btnAddExpense = findViewById<Button>(R.id.btnAddExpense)

        title.text = categoryName

        // ➕ OPEN ADD EXPENSE SCREEN
        btnAddExpense.setOnClickListener {
            val intent = Intent(this, AddExpenseActivity::class.java)
            intent.putExtra("category_name", categoryName)
            startActivity(intent)
        }

        // 🧠 LOAD FROM ROOM DATABASE (NO FAKE DATA)
        val db = WonderWalletDB.getDatabase(this)
        val dao = db.expenseDao()

        dao.getAllExpenses().observe(this) { allExpenses ->

            val filtered = allExpenses.filter {
                it.category == categoryName
            }

            val adapter = ExpenseAdapter(this, filtered)
            list.adapter = adapter
        }
    }
}