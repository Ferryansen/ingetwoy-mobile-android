package com.gabutmen.ingetwoy.data.repository

import com.gabutmen.ingetwoy.data.database.ProductDao
import com.gabutmen.ingetwoy.data.model.Product
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productDao: ProductDao
) {
    fun getAllProducts(): Flow<List<Product>> = productDao.getAll()

    suspend fun insertProduct(product: Product) = productDao.insert(product)
}