package com.example.wonderwallet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.wonderwallet.R
import com.example.wonderwallet.model.Expense

class ExpenseAdapter(
    private val context: Context,
    private val expenses: List<Expense>
) : BaseAdapter() {

    override fun getCount() = expenses.size

    override fun getItem(position: Int) = expenses[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_expense, parent, false)

        val amount = view.findViewById<TextView>(R.id.txtAmount)
        val title = view.findViewById<TextView>(R.id.txtTitle)
        val date = view.findViewById<TextView>(R.id.txtDate)

        val expense = expenses[position]

        amount.text = expense.amount
        title.text = expense.title
        date.text = expense.date

        return view
    }
}