package com.example.compose_material3_test.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.compose_material3_test.R
import com.example.compose_material3_test.components.GifImage
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.ImeAction

@Composable
fun Home(
    onNavigateToSearch: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var searchText by rememberSaveable {
            mutableStateOf("Warsaw")
        }
        
        Spacer(modifier = Modifier.height(40.dp))
        
        Card {
            GifImage(
                drawableResId = R.drawable.sun,
                modifier = Modifier
                    .padding(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
            },
            label = { Text("Search city or postcode")},
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onNavigateToSearch(searchText)
                }
            ),
            singleLine = true
        )
    }

}