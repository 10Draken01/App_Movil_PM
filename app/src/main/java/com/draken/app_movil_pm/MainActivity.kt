package com.draken.app_movil_pm

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.draken.app_movil_pm.core.di.AppModule
import com.draken.app_movil_pm.core.navigation.AppNavigation
import com.draken.app_movil_pm.ui.theme.App_Movil_PMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "Todo Ok")
        AppModule.init(applicationContext)
        enableEdgeToEdge()
        setContent {
            App_Movil_PMTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}
