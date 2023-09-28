package com.sagarika.features.presentation.views

 import MyApplication
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
 import androidx.lifecycle.ViewModel
 import androidx.navigation.compose.rememberNavController
import com.sagarika.features.presentation.navigation.NavGraph
import com.sagarika.features.presentation.viewmodels.ProductDetailsViewModel
 import com.sagarika.features.presentation.viewmodels.ProductListViewModel
 import dagger.hilt.android.AndroidEntryPoint
 import dagger.hilt.android.ViewModelLifecycle
 import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity()  {
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

