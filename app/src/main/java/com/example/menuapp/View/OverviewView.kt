package com.example.menuapp.View

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.menuapp.Model.Address
import com.example.menuapp.Model.Country
import com.example.menuapp.Model.Info
import com.example.menuapp.Model.MenuModel
import com.example.menuapp.Model.OfferedBurger
import com.example.menuapp.Model.OfferedDrink
import com.example.menuapp.ScreenNavigation.Screen
import com.example.menuapp.ViewModel.AppStateEnum
import com.example.menuapp.ViewModel.MenuViewModel
import com.example.menuapp.localMenuViewModel
import java.sql.Date

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun OverviewView(navController: NavController) {
    val menuViewModel = localMenuViewModel.current
    val menuState by menuViewModel.menuState.observeAsState()

    when (val state = menuState) {
        is AppStateEnum.failure -> {
            ErrorView(state)
        }

        AppStateEnum.idle -> {
            // EmptyState
        }

        AppStateEnum.loading -> {
            LoadingView()
        }

        is AppStateEnum.success -> {
            Scaffold(
                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(top = 30.dp)
                ) {
                    Text(
                        text = state.menu.name,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Row {
                        Text(
                            text = "${state.menu.address.street}, ",
                            fontSize = 20.sp
                        )
                        Text(
                            text = "${state.menu.address.city}, ",
                            fontSize = 20.sp
                        )
                        Text(
                            text = state.menu.address.country.name,
                            fontSize = 20.sp
                        )
                    }
                    Text(
                        text = "Burgers",
                        textAlign = TextAlign.Left,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 20.dp)
                    )

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f, fill = false)
                    ) {
                        items(state.menu.offeredBurgers) { burger ->
                            BurgerCellView(burger) {
                                navController.currentBackStackEntry
                                    ?.savedStateHandle
                                    ?.set("item", burger)
                                navController.navigate(Screen.Detail.route)
                            }
                        }
                    }
                    Text(
                        text = "Drinks",
                        textAlign = TextAlign.Left,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 20.dp)
                    )

                    LazyRow(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(state.menu.offeredDrinks) { drink ->
                            DrinkCellView(drink) {
                                navController.currentBackStackEntry
                                    ?.savedStateHandle
                                    ?.set("item", drink)
                                navController.navigate(Screen.Detail.route)
                            }
                        }
                    }
                }
            }
        }

        null -> {

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OverviewViewPreview() {
    // Dummy-Menü für Preview
    val mockData = MenuModel(
        name = "Tasty Burger!",
        address = Address(
            street = "Sesame Street 1",
            city = "Linz",
            country = Country(
                countryCode = "AT",
                name = "Austria"
            )
        ),
        offeredBurgers = listOf(
            OfferedBurger(
                name = "Big Burger",
                description = "This is a very tasty burger",
                weightGrams = 180,
                priceUsd = 9.99,
                info = Info(
                    inStock = true,
                    vegetarian = false,
                    allergensContainedIn = listOf("gluten", "milk"),
                    availableSince = 1605394800000,
                    availableUntil = Date.valueOf("2025-12-12"),
                    img = ""
                ),
                tags = listOf("bacon", "tomatoes")
            ),
            OfferedBurger(
                name = "Veggie Delight",
                description = "A healthy vegetarian burger with fresh veggies",
                weightGrams = 150,
                priceUsd = 8.49,
                info = Info(
                    inStock = true,
                    vegetarian = true,
                    allergensContainedIn = listOf("nuts"),
                    availableSince = 1610000000000,
                    availableUntil = Date.valueOf("2025-12-12"),
                    img = ""
                ),
                tags = listOf("veggie", "healthy")
            ),
            OfferedBurger(
                name = "Cheese Lover",
                description = "Loaded with different types of cheese",
                weightGrams = 200,
                priceUsd = 10.99,
                info = Info(
                    inStock = false,
                    vegetarian = false,
                    allergensContainedIn = listOf("gluten", "milk"),
                    availableSince = 1615000000000,
                    availableUntil = Date.valueOf("2025-12-12"),
                    img = ""
                ),
                tags = listOf("cheese", "rich")
            )
        ),
        offeredDrinks = listOf(
            OfferedDrink(
                name = "Long Island Ice Tea",
                description = "This is a nice cocktail",
                price = 4.99,
                allergens = listOf("rr"),
                img = ""
            ),
            OfferedDrink(
                name = "Coca-Cola",
                description = "Classic soft drink",
                price = 2.49,
                allergens = emptyList(),
                img = ""
            ),
            OfferedDrink(
                name = "Fresh Lemonade",
                description = "Refreshing lemonade made from real lemons",
                price = 3.49,
                allergens = emptyList(),
                img = ""
            ),
            OfferedDrink(
                name = "Fanta",
                description = "Refreshing lemonade made from real oranges",
                price = 3.00,
                allergens = emptyList(),
                img = ""
            )

        )
    )


    // Fake ViewModel mit Mock-State
    val mockViewModel = MenuViewModel().apply {
        setMockState(AppStateEnum.success(mockData))
    }

    val fakeNavController = rememberNavController()

    // Lokalen ViewModel-Context bereitstellen
    CompositionLocalProvider(localMenuViewModel provides mockViewModel) {
        OverviewView(navController = fakeNavController)
    }
}