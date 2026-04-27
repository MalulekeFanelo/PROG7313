package com.example.wonderwallet.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "monthly_budget")
data class MonthlyBudget(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    // Format: "2026-04" (Year-Month)
    val month: String,

    // Total budget for that month (e.g. 5000.0)
    val totalBudget: Float
)