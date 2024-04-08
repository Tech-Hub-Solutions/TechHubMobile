package com.example.techhub.common.composable

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.techhub.common.utils.startNewActivity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    willRedirectToActivity: Boolean,
    title: String,
    activity: Class<*>? = null,
    context: Context? = null,
    navController: NavController? = null,
    route: String? = null
) {
    val topAppBarColors = TopAppBarDefaults.topAppBarColors(
        containerColor = Color.Transparent,
        titleContentColor = MaterialTheme.colorScheme.primary,
    )
    val modifier = Modifier.padding(bottom = 8.dp)
    val navigationIcon: @Composable (() -> Unit)

    if (willRedirectToActivity) {
        requireNotNull(activity) { "Activity must not be null when willRedirectToActivity is true" }
        requireNotNull(context) { "Context must not be null when willRedirectToActivity is true" }

        navigationIcon = {
            IconButton(onClick = { startNewActivity(context!!, activity!!) }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIos,
                    contentDescription = "botão de retornar ao início",
                    tint = Color.Black
                )
            }
        }
    } else {
        requireNotNull(navController) { "NavController must not be null when willRedirectToActivity is false" }
        requireNotNull(route) { "Route must not be null when willRedirectToActivity is false" }

        navigationIcon = {
            IconButton(onClick = { navController.navigate(route) }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIos,
                    contentDescription = "botão de retornar ao início",
                    tint = Color.Black
                )
            }
        }
    }

    TopAppBar(
        colors = topAppBarColors,
        modifier = modifier,
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black,
                modifier = Modifier.background(Color.Transparent),
            )
        },
        navigationIcon = navigationIcon
    )
}
