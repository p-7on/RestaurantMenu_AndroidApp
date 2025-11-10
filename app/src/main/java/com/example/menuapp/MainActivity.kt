package com.example.menuapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.menuapp.ScreenNavigation.NavigationStack
import com.example.menuapp.View.OverviewView
import com.example.menuapp.ViewModel.MenuViewModel
import com.example.menuapp.ui.theme.MenuAppTheme

val localMenuViewModel = compositionLocalOf<MenuViewModel> {
    error("No ViewModel provided")
}

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val menuViewModel: MenuViewModel = viewModel()
            CompositionLocalProvider(localMenuViewModel provides menuViewModel) {
                NavigationStack()
            }
        }
    }
}