package com.example.wonderwallet

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.android.material.button.MaterialButton
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class Analysis : AppCompatActivity() {

    // Sample data
    private val transactions = listOf(
        Transaction("Food", 200.0, "2026-04-01", "Expense"),
        Transaction("Transport", 100.0, "2026-04-01", "Expense"),
        Transaction("Salary", 5000.0, "2026-04-01", "Income"),
        Transaction("Food", 150.0, "2026-04-02", "Expense")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_analysis)

        val btnFilter = findViewById<Button>(R.id.btnFilter)
        val etSearch = findViewById<EditText>(R.id.etSearch)

        //  Show totals when screen opens
        calculateTotals(transactions)

        //  Filter button
        btnFilter.setOnClickListener {
            val category = etSearch.text.toString()
            filterByCategory(category)
        }
        val btnDate = findViewById<MaterialButton>(R.id.btnDate)

        btnDate.setOnClickListener {

            val picker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds()) // 👈 current date
                .build()

            picker.show(supportFragmentManager, "DATE_PICKER")

            picker.addOnPositiveButtonClickListener { selection ->

                val format = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                val formatted = format.format(Date(selection))

                btnDate.text = formatted
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    //  Calculate totals
    private fun calculateTotals(transactions: List<Transaction>) {
        val totalIncome = transactions
            .filter { it.type == "Income" }
            .sumOf { it.amount }

        val totalExpense = transactions
            .filter { it.type == "Expense" }
            .sumOf { it.amount }

        findViewById<TextView>(R.id.tvTotalIncome).text = "Income: R$totalIncome"
        findViewById<TextView>(R.id.tvTotalExpense).text = "Expenses: R$totalExpense"
    }

    //  Filter function
    private fun filterByCategory(category: String) {
        val resultView = findViewById<TextView>(R.id.tvResults)

        val filtered = transactions.filter {
            it.category.contains(category, ignoreCase = true)
        }

        val totals = filtered
            .filter { it.type == "Expense" }
            .groupBy { it.category }
            .mapValues { it.value.sumOf { t -> t.amount } }

        resultView.text = totals.entries.joinToString("\n") {
            "${it.key}: R${it.value}"
        }
        val barChart = findViewById<BarChart>(R.id.barChart)

        val entries = listOf(
            BarEntry(1f, 500f),
            BarEntry(2f, 800f),
            BarEntry(3f, 300f),
            BarEntry(4f, 900f)
        )

        val dataSet = BarDataSet(entries, "Spending")
        dataSet.color = Color.MAGENTA

        val data = BarData(dataSet)
        barChart.data = data
        barChart.invalidate()
    }


}