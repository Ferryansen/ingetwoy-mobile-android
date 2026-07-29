package com.gabutmen.ingetwoy.ui.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabutmen.ingetwoy.data.model.Product
import com.gabutmen.ingetwoy.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

@HiltViewModel
class AddFormViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    fun onSaveClicked(name: String, category: String, notes: String, purchaseDate: Long?, expirationDate: Long?) {
        insertProduct(Product(
            name = name,
            category = category,
            purchaseDate = dateLongToLocalConverter(purchaseDate),
            expirationDate = dateLongToLocalConverter(expirationDate),
            notes = if(notes != "") notes else null
        ))
    }

    fun insertProduct(product: Product) {
        viewModelScope.launch {
            productRepository.insertProduct(product)
        }
    }

    private fun dateLongToLocalConverter(millis: Long?): LocalDate {
        return Instant.ofEpochMilli(requireNotNull(millis)).atZone(ZoneId.systemDefault()).toLocalDate()
    }
}