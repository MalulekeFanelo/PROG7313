package com.example.wonderwallet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.wonderwallet.R
import com.example.wonderwallet.model.Category
import android.content.Intent
import com.example.wonderwallet.CategoryExpensesActivity

class CategoryAdapter(
    private val context: Context,
    private val categories: List<Category>
) : BaseAdapter() {

    override fun getCount(): Int {
        return categories.size
    }

    override fun getItem(position: Int): Any {
        return categories[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_category, parent, false)

        val name = view.findViewById<TextView>(R.id.txtCategoryName)
        name.text = categories[position].name

        // ⭐ CLICK EVENT ADDED HERE
        view.setOnClickListener {

            val intent = Intent(context, CategoryExpensesActivity::class.java)

            intent.putExtra("category_name", categories[position].name)

            context.startActivity(intent)
        }

        return view
    }
}
