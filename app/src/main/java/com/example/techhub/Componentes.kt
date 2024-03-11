package com.example.techhub

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun BottomAppBarExample() {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton(onClick = { /* do something */ }) {
                        Icon(Icons.Filled.Check, contentDescription = "Localized description")
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            Icons.Filled.Edit,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            Icons.Filled.AccountBox,
                            contentDescription = "Localized description",
                        )
                    }
                    IconButton(onClick = { /* do something */ }) {
                        Icon(
                            Icons.Filled.AddCircle,
                            contentDescription = "Localized description",
                        )
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { /* do something */ },
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(Icons.Filled.Add, "Localized description")
                    }
                }
            )
        },
    ) { innerPadding ->
        Text(
            modifier = Modifier.padding(innerPadding),
            text = "Example of a scaffold with a bottom app bar."
        )
    }
}

@Composable
fun DefaultButton(height: Double, width: Double, text: String){
    Button(onClick = { /*TODO*/ },
        modifier = Modifier
            .height(height.dp)
            .width(width.dp),
        shape = MaterialTheme.shapes.small,
    ) {
        Text(text = text)
    }
}

@Composable
fun EditableForm(height: Double, width: Double, backcroundColor: Color,
                 imagePath: Int, contentDescription: String, text: String, textColor : Color) {
    Box(modifier = Modifier
        .height(height.dp)
        .width(width.dp)
        .background(backcroundColor, shape = MaterialTheme.shapes.extraSmall),
        contentAlignment = Alignment.TopCenter

    ) {
        Column (
        ) {
            Image(painter = painterResource(id = imagePath),
                contentDescription = contentDescription,
                modifier = Modifier.height(116.dp).width(106.dp)
            )
            Text(text = text,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                color = textColor
            )

        }
    }
}

@Composable
fun EditableInput(mutableValue : MutableState<String>, onChange : (TextFieldValue) -> Unit,
                  label : String,
                  supportingText : @Composable (() -> Unit)? = null){
    val frase = mutableValue
    TextField(value = frase.value,
        onValueChange = {onChange},
        label = { Text(text = label)},
        supportingText = {supportingText},
        shape = MaterialTheme.shapes.small
    )
}