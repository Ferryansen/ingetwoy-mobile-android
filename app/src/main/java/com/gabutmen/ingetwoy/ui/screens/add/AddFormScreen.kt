package com.gabutmen.ingetwoy.ui.screens.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFormScreen(navController: NavHostController) {
    var productName by rememberSaveable { mutableStateOf("") }
    var notes by rememberSaveable { mutableStateOf("") }
    var purchaseDate by rememberSaveable { mutableStateOf<Long?>(null) }
    var expirationDate by rememberSaveable { mutableStateOf<Long?>(null) }

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

            Text("Reminder related field will be updated shortly")
        }
    }
}