package com.example.wonderwallet

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class AddExpenseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_expense)

        val editDate = findViewById<EditText>(R.id.editDate)
        val editAmount = findViewById<EditText>(R.id.editAmount)
        val editTitle = findViewById<EditText>(R.id.editTitle)
        val editMessage = findViewById<EditText>(R.id.editMessage)
        val btnSave = findViewById<Button>(R.id.btnSave)

        // 📅 DATE PICKER
        editDate.setOnClickListener {
            val calendar = Calendar.getInstance()

            val datePicker = DatePickerDialog(
                this,
                { _, year, month, day ->
                    val date = "$day/${month + 1}/$year"
                    editDate.setText(date)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            datePicker.show()
        }

        // 💾 SAVE BUTTON (EVENT HANDLING)
        btnSave.setOnClickListener {

            val title = editTitle.text.toString()
            val amount = editAmount.text.toString()
            val date = editDate.text.toString()

            Toast.makeText(this, "Expense Saved", Toast.LENGTH_SHORT).show()

            // Later: save to RoomDB here
        }
    }
}