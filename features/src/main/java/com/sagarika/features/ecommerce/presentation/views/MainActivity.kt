package com.sagarika.features.ecommerce.presentation.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.rememberNavController
import com.sagarika.features.ecommerce.presentation.navigation.NavGraph
import com.sagarika.features.ecommerce.presentation.theme.MyApplication
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // inject view models here
        super.onCreate(savedInstanceState)
        setContent {
            MyApplication {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    // at below line we are calling
                    // our function for toolbar.

                    val navController = rememberNavController()
                    NavGraph(navController = navController)
                }
            }
        }
    }
}

