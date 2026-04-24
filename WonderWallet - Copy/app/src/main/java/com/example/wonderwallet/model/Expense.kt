package com.example.wonderwallet.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date


@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val category: String,
    val date: Long,
    val description: String,
    val title: String,
    val amount: Float
)
