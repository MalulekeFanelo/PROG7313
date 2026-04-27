package com.example.wonderwallet

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.wonderwallet.model.Expense
import com.example.wonderwallet.model.WonderWalletDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class AddExpenseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        val editDate = findViewById<EditText>(R.id.editDate)
        val editAmount = findViewById<EditText>(R.id.editAmount)
        val editTitle = findViewById<EditText>(R.id.editTitle)
        val editMessage = findViewById<EditText>(R.id.editMessage)
        val editCategory = findViewById<EditText>(R.id.editCategory)
        val btnSave = findViewById<Button>(R.id.btnSave)

        val categoryFromIntent = intent.getStringExtra("category_name")
        editCategory.setText(categoryFromIntent)

        // 📅 DATE PICKER
        editDate.setOnClickListener {
            val calendar = Calendar.getInstance()

            DatePickerDialog(
                this,
                { _, year, month, day ->
                    editDate.setText("$day/${month + 1}/$year")
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        // 💾 SAVE EXPENSE
        btnSave.setOnClickListener {

            val title = editTitle.text.toString()
            val amount = editAmount.text.toString().toFloatOrNull() ?: 0f
            val description = editMessage.text.toString()
            val category = editCategory.text.toString()

            val calendar = Calendar.getInstance()
            val dateParts = editDate.text.toString().split("/")

            if (dateParts.size == 3) {
                val day = dateParts[0].toInt()
                val month = dateParts[1].toInt() - 1
                val year = dateParts[2].toInt()
                calendar.set(year, month, day)
            }

            val timestamp = calendar.timeInMillis

            val expense = Expense(
                category = category,
                date = timestamp,
                description = description,
                title = title,
                amount = amount
            )

            // 🧠 ROOM INSERT
            val db = WonderWalletDB.getDatabase(this)
            val dao = db.expenseDao()

            CoroutineScope(Dispatchers.IO).launch {
                dao.insert(expense)

                runOnUiThread {
                    Toast.makeText(this@AddExpenseActivity, "Expense Saved", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}