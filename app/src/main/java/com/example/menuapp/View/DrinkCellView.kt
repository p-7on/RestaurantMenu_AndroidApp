package com.example.menuapp.View

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import com.example.menuapp.Model.Info
import com.example.menuapp.Model.OfferedBurger
import com.example.menuapp.Model.OfferedDrink
import com.example.menuapp.R

@Composable
fun DrinkCellView(drink: OfferedDrink, onClick: () -> Unit) {

    val imageModel = if (LocalInspectionMode.current) {
        R.drawable.drink
    } else {
        drink.img
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() }
            .padding(top = 16.dp, end = 16.dp)
    ) {
        AsyncImage(
            model = imageModel,
            contentDescription = "drink_image: ${drink.name}",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(CornerSize(20.dp)))
                .background(Color.LightGray)
        )
        Text(
            text = drink.name,
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .sizeIn(maxWidth = 100.dp)
                .padding(top = 10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DrinkCellViewPreview() {
    DrinkCellView(
        drink = OfferedDrink(
            name = "Long island ice tea",
            description = "This is a nice cocktail",
            price = 4.99,
            allergens = listOf("rr"),
            img = ""
        ),
        onClick = {},
    )
}