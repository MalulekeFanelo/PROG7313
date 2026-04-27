package com.example.wonderwallet.model

import androidx.lifecycle.LiveData


@Dao
interface MonthlyBudgetDAO {

    // 💰 Insert a monthly budget
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMonthlyBudget(budget: MonthlyBudget)

    // 📅 Get budget for a specific month
    @Query("SELECT * FROM monthly_budget WHERE month = :month LIMIT 1")
    fun getMonthlyBudget(month: String): LiveData<MonthlyBudget>

    // ✏ Update monthly budget
    @Update
    suspend fun updateMonthlyBudget(budget: MonthlyBudget)

    // 🗑 Delete monthly budget
    @Delete
    suspend fun deleteMonthlyBudget(budget: MonthlyBudget)

    // Get all budgets (useful for history screen)
    @Query("SELECT * FROM monthly_budget ORDER BY month DESC")
    fun getAllMonthlyBudgets(): LiveData<List<MonthlyBudget>>

    //  Check if budget exists for a month (prevents duplicates in UI logic)
    @Query("SELECT COUNT(*) FROM monthly_budget WHERE month = :month")
    suspend fun budgetExists(month: String): Int
}