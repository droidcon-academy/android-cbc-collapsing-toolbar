package com.rodrigoguerrero.collapsibletoolbars

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.rodrigoguerrero.collapsibletoolbars.ui.navigation.RootNavGraph
import com.rodrigoguerrero.collapsibletoolbars.ui.theme.CollapsibleToolbarsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CollapsibleToolbarsTheme {
                RootNavGraph(navHostController = rememberNavController())
            }
        }
    }
}
