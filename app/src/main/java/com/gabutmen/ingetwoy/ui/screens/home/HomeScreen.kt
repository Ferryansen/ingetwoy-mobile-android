package com.gabutmen.ingetwoy.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.gabutmen.ingetwoy.navigation.Routes

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.padding(
            horizontal = 16.dp,
            vertical = 48.dp
        )
    ) {
        Text("Welcome Home, for real!")

        Button(
            onClick = {
                navController.navigate(Routes.ADD_FORM)
            }
        ) {
            Text("Add")
        }
    }
}