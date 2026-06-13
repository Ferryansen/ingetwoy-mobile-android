package com.gabutmen.ingetwoy.ui.screens.home

import androidx.lifecycle.ViewModel
import com.gabutmen.ingetwoy.data.Product
import com.gabutmen.ingetwoy.data.ReminderOffset
import com.gabutmen.ingetwoy.data.ReminderUnit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate

class HomeViewModel: ViewModel() {
    private val _items = MutableStateFlow<List<Product>>(
        listOf(
            Product(
                id = 1L,
                name = "iPhone 17",
                category = "Gadget",
                purchaseDate = LocalDate.of(2026,6,12),
                expirationDate = LocalDate.of(2027,6,12),
                notes = null,
                extraReminders = listOf(
                    ReminderOffset(
                        14,
                        ReminderUnit.DAY
                    )
                )
            ),
            Product(
                id = 2L,
                name = "Samsung TV 32 Inch",
                category = "Home Appliance",
                purchaseDate = LocalDate.of(2026,4,21),
                expirationDate = LocalDate.of(2028,4,11),
                notes = "Check any dots problems",
                extraReminders = listOf(
                    ReminderOffset(
                        1,
                        ReminderUnit.YEAR
                    ),
                    ReminderOffset(
                        1,
                        ReminderUnit.MONTH
                    ),
                    ReminderOffset(
                        14,
                        ReminderUnit.DAY
                    ),
                    ReminderOffset(
                        7,
                        ReminderUnit.DAY
                    )
                )
            )
        )
    )
    val items: StateFlow<List<Product>> = _items
}