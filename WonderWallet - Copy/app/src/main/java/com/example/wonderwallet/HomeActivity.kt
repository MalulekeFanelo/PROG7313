package com.example.wonderwallet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.wonderwallet.data.AppDatabase
import com.example.wonderwallet.AddExpenseActivity
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {

    private lateinit var txtWelcome: TextView
    private lateinit var txtBalance: TextView
    private lateinit var btnAddExpense: Button
    private lateinit var btnChatbot: Button

    private val monthlyIncome = 7850.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        txtWelcome = findViewById(R.id.txtWelcome)
        txtBalance = findViewById(R.id.txtBalance)
        btnAddExpense = findViewById(R.id.btnAddExpense)
        btnChatbot = findViewById(R.id.btnChatbot)

        val username = intent.getStringExtra("USERNAME") ?: "User"
        txtWelcome.text = "Welcome, $username"

        loadBalance()

        btnAddExpense.setOnClickListener {
            startActivity(Intent(this, AddExpenseActivity::class.java))
        }

        btnChatbot.setOnClickListener {
            startActivity(Intent(this, ChatbotActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        loadBalance()
    }

    private fun loadBalance() {
        val expenseDao = AppDatabase.getDatabase(this).expenseDao()

        lifecycleScope.launch {
            val totalExpenses = expenseDao.getTotalExpenses() ?: 0.0
            val balance = monthlyIncome - totalExpenses
            txtBalance.text = "R%.2f".format(balance)
        }
    }
}