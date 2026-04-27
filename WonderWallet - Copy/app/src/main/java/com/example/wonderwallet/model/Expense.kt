package com.example.wonderwallet.model

import androidx.room.Entity
import androidx.room.PrimaryKey
<<<<<<< HEAD
import java.util.Date

=======
>>>>>>> 795d7939929ee39dc821bb3f7c9d7adf789c4e7b

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    val category: String,

    val date: Long,

    val description: String,

    val title: String,

    val amount: Float,

    // 📸 Stores image URI/path of receipt (can be null if no receipt added)
    val receiptImageUri: String? = null
)