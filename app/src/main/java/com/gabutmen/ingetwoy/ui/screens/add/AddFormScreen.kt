package com.gabutmen.ingetwoy.ui.screens.add

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.type
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gabutmen.ingetwoy.navigation.Routes
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

enum class DateField {
    PURCHASE,
    EXPIRE
}

private fun dateConverter(millis: Long?): String {
    if(millis == null) return "Select date"
    val date = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDate()

    return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFormScreen(navController: NavHostController) {
    var productName by rememberSaveable { mutableStateOf("") }
    var notes by rememberSaveable { mutableStateOf("") }

    var purchaseDate by rememberSaveable { mutableStateOf<Long?>(null) }
    val purchaseDatePickerState = rememberDatePickerState()
    var expirationDate by rememberSaveable { mutableStateOf<Long?>(null) }
    val expirationDatePickerState = rememberDatePickerState()
    var currPicker: DateField? by remember { mutableStateOf(null) }

    val categories = listOf("Gadget", "Home Appliances", "Others")
    var selectedCategory by rememberSaveable { mutableStateOf(categories[0]) }
    var expanded by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Tambah Produk")
                },
                navigationIcon = {
                    Button(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Text("Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TextField(
                value = productName,
                onValueChange = {productName = it},
                label = {Text("Product Name")}
            )

            TextField(
                value = notes,
                onValueChange = {notes = it},
                label = {Text("Notes")},

                minLines = 3,
                maxLines = 6
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {expanded = !expanded}
            ) {
                TextField(
                    value = selectedCategory,
                    onValueChange = {},
                    readOnly = true,
                    label = {Text("Category")},
                    modifier = Modifier.menuAnchor(type = ExposedDropdownMenuAnchorType.PrimaryNotEditable)
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {expanded = false}
                ) {
                    categories.forEach { category ->
                        DropdownMenuItem(
                            text = {Text(category)},
                            onClick = {
                                selectedCategory = category
                                expanded = false
                            }
                        )
                    }
                }
            }

            Box(
                modifier = Modifier.clickable { currPicker = DateField.PURCHASE }
            ) {
                TextField(
                    value = dateConverter(purchaseDate),
                    onValueChange = {},
                    readOnly = true,
                    enabled = false,
                    label = {Text("Purchase Date")},
                )
            }

            Box(
                modifier = Modifier.clickable { currPicker = DateField.EXPIRE }
            ) {
                TextField(
                    value = dateConverter(expirationDate),
                    onValueChange = {},
                    readOnly = true,
                    enabled = false,
                    label = {Text("Expiration Date")}
                )
            }


            Text("Reminder related field will be updated shortly")
        }
    }

    if(currPicker != null) {
        DatePickerDialog(
            onDismissRequest = { currPicker = null },
            confirmButton = {
                TextButton(
                    onClick = {
                        if(currPicker == DateField.PURCHASE) {
                            purchaseDate = purchaseDatePickerState.selectedDateMillis
                        } else if(currPicker == DateField.EXPIRE) {
                            expirationDate = expirationDatePickerState.selectedDateMillis
                        }

                        currPicker = null
                    }
                ) {
                    Text("Ok!")
                }
            }
        ) {
            DatePicker(
                state = if (currPicker == DateField.PURCHASE) {
                    purchaseDatePickerState
                } else {
                    expirationDatePickerState
                }
            )
        }
    }
}