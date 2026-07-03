package com.gabutmen.ingetwoy.ui.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabutmen.ingetwoy.data.model.Product
import com.gabutmen.ingetwoy.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddFormViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {
    fun insertProduct(product: Product) {
        viewModelScope.launch {
            productRepository.insertProduct(product)
        }
    }
}