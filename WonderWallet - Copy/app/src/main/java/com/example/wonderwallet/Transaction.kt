package com.example.wonderwallet

data class Transaction(

val category: String,
val amount: Double,
val date: String,
val type: String // "Income" or "Expense"
)


