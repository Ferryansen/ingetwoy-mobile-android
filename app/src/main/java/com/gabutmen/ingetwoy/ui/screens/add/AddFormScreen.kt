package com.gabutmen.ingetwoy.ui.screens.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun AddFormScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 48.dp
        )
    ) {
        Row() {
            Button(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Text("Back")
            }
            Spacer(modifier = Modifier.padding(16.dp))
            Text("Additional at best, BOI")
        }
    }
}