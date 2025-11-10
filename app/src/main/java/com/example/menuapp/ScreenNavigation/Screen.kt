package com.example.menuapp.ScreenNavigation

sealed class Screen(val route: String) {
    object Overview: Screen("overview_screen")
    object Detail: Screen("details_screen")
}