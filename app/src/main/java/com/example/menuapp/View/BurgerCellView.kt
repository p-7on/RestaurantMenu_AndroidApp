package com.example.menuapp.View

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.menuapp.Model.Info
import com.example.menuapp.Model.OfferedBurger
import com.example.menuapp.R

// Fake ViewModel für Preview
class FakeMenuViewModel {
    fun isAvailable(burger: OfferedBurger) = burger.info.inStock
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BurgerCellView(
    burger: OfferedBurger,
    viewModel: Any? = null, // Optional für Preview
    onClick: () -> Unit
) {
    // Für Preview -> FakeViewModel
    val actualViewModel = viewModel ?: FakeMenuViewModel()

    val available = if (actualViewModel is FakeMenuViewModel) {
        actualViewModel.isAvailable(burger)
    } else {
        burger.info.inStock
    }

    val imageModel = if (LocalInspectionMode.current) {
        R.drawable.burger
    } else {
        burger.info.img
    }

    if (available == true) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clickable { onClick() }
                .fillMaxWidth()
                .padding(top = 16.dp)
                .shadow(elevation = 1.dp, shape = RoundedCornerShape(20.dp))
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = imageModel,
                    contentDescription = "burger_image: ${burger.name}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(120.dp)
                        .background(Color.LightGray)
                )
                Text(
                    text = "${burger.priceUsd} USD",
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.BottomEnd)
                        .clip(RoundedCornerShape(CornerSize(20.dp)))
                        .background(Color.Cyan)
                        .padding(start = 20.dp, end = 20.dp, top = 5.dp, bottom = 5.dp)
                )
            }
            Row(
                modifier = Modifier
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(text = burger.name, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.weight(1f))
                burger.weightGrams?.let {
                    Text(text = "${it}g", color = Color.Gray, fontWeight = FontWeight.Bold)
                }
            }
        }
    } else {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .shadow(elevation = 1.dp, shape = RoundedCornerShape(20.dp))
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = imageModel,
                    contentDescription = "burger_image: ${burger.name}",
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToSaturation(0f) }),
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(120.dp)
                        .background(Color.LightGray)
                )

                Text(
                    text = "Currently not available",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clip(RoundedCornerShape(CornerSize(20.dp)))
                        .background(Color.LightGray)
                        .padding(start = 24.dp, end = 24.dp, top = 10.dp, bottom = 10.dp)
                )
            }
            Row(
                modifier = Modifier
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Text(
                    text = burger.name,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(textDecoration = TextDecoration.LineThrough)
                )
                Spacer(modifier = Modifier.weight(1f))
                burger.weightGrams?.let {
                    Text(
                        text = "${it}g",
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(textDecoration = TextDecoration.LineThrough)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, apiLevel = 31)
@Composable
fun BurgerCellViewPreview() {
    val fakeViewModel = FakeMenuViewModel()

    BurgerCellView(
        burger = OfferedBurger(
            name = "Big Burger",
            description = "This is a very tasty burger!",
            weightGrams = 280,
            priceUsd = 9.99,
            info = Info(
                inStock = true,
                vegetarian = false,
                allergensContainedIn = listOf("aa", "xx"),
                availableSince = 1605394800000,
                availableUntil = null,
                img = ""
            ),
            tags = listOf("bacon", "tomatoes")
        ),
        onClick = {},
        viewModel = fakeViewModel
    )
}