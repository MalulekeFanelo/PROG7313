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
}
