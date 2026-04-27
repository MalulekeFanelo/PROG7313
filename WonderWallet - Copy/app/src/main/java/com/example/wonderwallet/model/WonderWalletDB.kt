package com.example.wonderwallet.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Expense::class], version = 1, exportSchema = false)
abstract class WonderWalletDB : RoomDatabase() {

    abstract fun expenseDao(): ExpenseDAO

    companion object {

        @Volatile
        private var INSTANCE: WonderWalletDB? = null

        fun getDatabase(context: Context): WonderWalletDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WonderWalletDB::class.java,
                    "wonder_wallet_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}