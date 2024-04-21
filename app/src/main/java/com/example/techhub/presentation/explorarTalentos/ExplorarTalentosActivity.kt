package com.example.techhub.presentation.explorarTalentos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.techhub.common.Screen
import com.example.techhub.composable.SetBarColor
import com.example.techhub.presentation.explorarTalentos.composable.ExplorarTalentosView
import com.example.techhub.presentation.favoritos.composables.FavoritosView
import com.example.techhub.ui.theme.TechHubTheme

class ExplorarTalentosActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TechHubTheme {
                SetBarColor(color = Color.White)

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ExplorarTalentosView.route,
                        route = Screen.ExplorarTalentosView.route
                    ) {
                        composable(route = Screen.ExplorarTalentosView.route) {
                            Surface(
                                modifier = Modifier.fillMaxSize(),
                                color = MaterialTheme.colorScheme.background
                            ) {
                                ExplorarTalentosView()
                            }
                        }
                    }
                }
            }
        }
    }
}