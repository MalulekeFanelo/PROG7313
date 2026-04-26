package com.example.wonderwallet.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        Expense::class,
        MonthlyBudget::class,
        Category::class
    ],
    version = 1,
    exportSchema = false
)
abstract class WonderWalletDB : RoomDatabase() {

    // 📦 DAOs
    abstract fun expenseDao(): ExpenseDAO
    abstract fun monthlyBudgetDao(): MonthlyBudgetDAO

    abstract fun categoryDao(): CategoryDAO

    companion object {

        @Volatile
        private var INSTANCE: WonderWalletDB? = null

        fun getDatabase(context: Context): WonderWalletDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WonderWalletDB::class.java,
                    "wonder_wallet_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}