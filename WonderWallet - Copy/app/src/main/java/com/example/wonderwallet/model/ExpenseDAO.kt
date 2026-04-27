package com.example.wonderwallet.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ExpenseDAO {
    @Insert
    suspend fun insert(expense: Expense)
    @Update
    suspend fun update(expense: Expense)
    @Delete
    suspend fun delete(expense: Expense)
    @Query("SELECT * FROM expenses")
    fun getAllExpenses(): LiveData<List<Expense>>

    //  Filter by date range (VERY IMPORTANT)
    @Query("SELECT * FROM expenses WHERE date BETWEEN :start AND :end")
    fun getExpensesByDateRange(start: Long, end: Long): LiveData<List<Expense>>

    //  Category total for period
    @Query("SELECT COALESCE(SUM(amount), 0) FROM expenses WHERE category = :category AND date BETWEEN :start AND :end")
    fun getCategoryTotal(category: String, start: Long, end: Long): LiveData<Float>
}

