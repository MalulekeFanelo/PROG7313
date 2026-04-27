package com.example.wonderwallet

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.wonderwallet.data.AppDatabase
import com.example.wonderwallet.data.Expense
import kotlinx.coroutines.launch
import java.util.Calendar

class AddExpenseActivity : AppCompatActivity() {

    private lateinit var edtAmount: EditText
    private lateinit var edtCategory: EditText
    private lateinit var edtDate: EditText
    private lateinit var edtDescription: EditText
    private lateinit var btnSaveExpense: Button
    private lateinit var btnBackHome: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        // Link XML IDs
        edtAmount = findViewById(R.id.edtAmount)
        edtCategory = findViewById(R.id.edtCategory)
        edtDate = findViewById(R.id.edtDate)
        edtDescription = findViewById(R.id.edtDescription)
        btnSaveExpense = findViewById(R.id.btnSaveExpense)
        btnBackHome = findViewById(R.id.btnBackHome)

        // Room DB
        val expenseDao = AppDatabase.getDatabase(this).expenseDao()

        // Date Picker
        edtDate.setOnClickListener {
            showDatePicker()
        }

        // Save Expense
        btnSaveExpense.setOnClickListener {

            val amountText = edtAmount.text.toString().trim()
            val category = edtCategory.text.toString().trim()
            val date = edtDate.text.toString().trim()
            val description = edtDescription.text.toString().trim()

            if (amountText.isEmpty() || category.isEmpty() || date.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val amount = amountText.toDoubleOrNull()

            if (amount == null || amount <= 0) {
                Toast.makeText(this, "Enter a valid amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {

                val expense = Expense(
                    amount = amount,
                    category = category,
                    date = date,
                    description = description
                )

                expenseDao.insertExpense(expense)

                Toast.makeText(this@AddExpenseActivity, "Expense saved", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this@AddExpenseActivity, HomeActivity::class.java))
                finish()
            }
        }

        // Back Button
        btnBackHome.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()

        val picker = DatePickerDialog(
            this,
            { _, year, month, day ->
                val selectedDate = "$day/${month + 1}/$year"
                edtDate.setText(selectedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        picker.show()
    }
}