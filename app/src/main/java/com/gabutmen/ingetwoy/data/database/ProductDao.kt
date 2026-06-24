package com.gabutmen.ingetwoy.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gabutmen.ingetwoy.data.model.Product
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query(
        "SELECT * FROM products"
    )
    fun getAll(): Flow<List<Product>>

    @Insert
    suspend fun insert(product: Product)
}