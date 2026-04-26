package com.example.wonderwallet.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    // Example: "Food", "Transport", "Entertainment"
    val name: String
)