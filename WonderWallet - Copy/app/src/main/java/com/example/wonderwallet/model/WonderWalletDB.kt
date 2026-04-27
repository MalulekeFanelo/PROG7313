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

<<<<<<< HEAD
    abstract fun expenseDao(): ExpenseDAO
=======
    // 📦 DAOs
    abstract fun expenseDao(): ExpenseDAO
    abstract fun monthlyBudgetDao(): MonthlyBudgetDAO

    abstract fun categoryDao(): CategoryDAO
>>>>>>> 795d7939929ee39dc821bb3f7c9d7adf789c4e7b

    companion object {

        @Volatile
        private var INSTANCE: WonderWalletDB? = null

        fun getDatabase(context: Context): WonderWalletDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WonderWalletDB::class.java,
                    "wonder_wallet_database"
<<<<<<< HEAD
                ).build()
=======
                )
                    .fallbackToDestructiveMigration()
                    .build()
>>>>>>> 795d7939929ee39dc821bb3f7c9d7adf789c4e7b

                INSTANCE = instance
                instance
            }
        }
    }
}