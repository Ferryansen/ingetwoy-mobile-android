package com.gabutmen.ingetwoy.data.repository

import com.gabutmen.ingetwoy.data.database.ProductDao
import com.gabutmen.ingetwoy.data.model.Product
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val productDao: ProductDao) {
    fun getAllProducts(): Flow<List<Product>> = productDao.getAll()
}