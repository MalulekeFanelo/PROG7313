package com.example.wonderwallet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.wonderwallet.R
import com.example.wonderwallet.model.Expense
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class ExpenseAdapter(
    private val context: Context,
    private val expenses: List<Expense>
) : BaseAdapter() {

    // ✅ Create once (better performance)
    private val currencyFormatter = NumberFormat.getCurrencyInstance(Locale("en", "ZA"))
    private val dateFormatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

    override fun getCount(): Int = expenses.size

    override fun getItem(position: Int): Any = expenses[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_expense, parent, false)

        val amount = view.findViewById<TextView>(R.id.txtAmount)
        val title = view.findViewById<TextView>(R.id.txtTitle)
        val date = view.findViewById<TextView>(R.id.txtDate)

        val expense = expenses[position]

        // 💰 Currency formatting (NumberFormat requirement ✔)
        amount.text = currencyFormatter.format(expense.amount)

        // 🏷 Title
        title.text = expense.title

        // 📅 Date formatting from timestamp
        date.text = dateFormatter.format(Date(expense.date))

        return view
    }
}