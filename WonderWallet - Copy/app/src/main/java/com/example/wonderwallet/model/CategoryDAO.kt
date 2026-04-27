package com.example.wonderwallet.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CategoryDAO {

    // ➕ Insert single category
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)

    // ➕ Insert multiple categories (useful for default setup)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categories: List<Category>)

    // 📊 Get all categories (for GridView / UI)
    @Query("SELECT * FROM category ORDER BY name ASC")
    fun getAllCategories(): LiveData<List<Category>>

    // 🔍 Get single category by ID
    @Query("SELECT * FROM category WHERE id = :id LIMIT 1")
    fun getCategoryById(id: Int): LiveData<Category>

    // ✏️ Update category
    @Update
    suspend fun updateCategory(category: Category)

    // 🗑 Delete category
    @Delete
    suspend fun deleteCategory(category: Category)

    // ⚠️ Check if category exists (prevents duplicates)
    @Query("SELECT COUNT(*) FROM category WHERE name = :name")
    suspend fun categoryExists(name: String): Int
}