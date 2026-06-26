package com.gabutmen.ingetwoy.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabutmen.ingetwoy.data.model.Product
import com.gabutmen.ingetwoy.data.repository.ProductRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(private val productRepository: ProductRepository): ViewModel() {
    private val _items = productRepository.getAllProducts().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
    val items: StateFlow<List<Product>> = _items
}