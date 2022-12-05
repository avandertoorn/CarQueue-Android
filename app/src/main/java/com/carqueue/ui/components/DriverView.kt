package com.carqueue.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DriverView(
) {
    val viewModel: DriverViewModel = viewModel()
    val drivers = viewModel.drivers
    Column(){
        SearchText(viewModel::onSearchTextChanged)
        LazyColumn(){
            items(drivers) { driver ->
                DriverCard(driver, viewModel::onQueueDriver)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchText(
    onSearchTextChanged: (String) -> Unit
) {
    var textFieldState by remember {
        mutableStateOf( "")
    }

    fun updateTextField(text: String){
        textFieldState = text
        onSearchTextChanged(text)
    }

    OutlinedTextField(
        value = textFieldState,
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.primary,
            fontSize = 25.sp),
        onValueChange = {
            updateTextField(it) },
        label = { Text(
            text = "Search")
        },
        shape = RoundedCornerShape(10.dp),
        leadingIcon = { Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = "Search Icon" )
        },
        trailingIcon = { IconButton(onClick = {
            updateTextField("") }) {
            Icon(
                imageVector = Icons.Filled.Clear,
                contentDescription = "Clear text" )
        }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth())
}

@Preview(showBackground = true)
@Composable
fun PreviewDriverView() {
    DriverView()
}