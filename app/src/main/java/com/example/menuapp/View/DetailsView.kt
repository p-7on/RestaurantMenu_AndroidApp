package com.example.menuapp.View

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.menuapp.Model.ItemDetails
import com.example.menuapp.Model.OfferedDrink
import com.example.menuapp.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsView(item: ItemDetails, navigateBack: () -> Unit) {

    val imageModel = if (LocalInspectionMode.current) {
        R.drawable.drink
    } else {
        item.itemImg
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = item.itemName,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
            ) {
                AsyncImage(
                    model = imageModel,
                    contentDescription = "image_of_${item.itemName}",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .fillMaxWidth()
                        .size(240.dp)
                        .background(Color.LightGray)

                )

                Row(
                    modifier = Modifier
                        .padding(top = 16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = item.itemPrice.toString() + " USD",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Price",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray,
                        )
                    }
                    if (item.itemWeightGrams != null) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(
                                text = item.itemWeightGrams.toString() + "g",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Weight",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Gray,
                            )
                        }
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        Text(
                            text = if (item.itemIsVegetarian == true) "Yes" else "No",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Vegetarian",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Gray,
                        )
                    }
                }

                HorizontalDivider(
                    thickness = 1.dp,
                    modifier = Modifier
                        .padding(top = 16.dp, bottom = 16.dp)
                    )

                Text(text = item.itemDescription)

                if (!item.itemTags.isNullOrEmpty()) {
                    Column {
                        Text(
                            text = "Special Ingredients",
                            color = Color.Gray,
                            modifier = Modifier
                                .padding(top = 24.dp)
                        )
                        Text(
                            text = item.itemTags!!.joinToString(separator = ", "),
                            modifier = Modifier
                                .padding(top = 4.dp)
                        )
                    }
                }

                if (!item.itemAllergens.isNullOrEmpty()) {
                    Column {
                        Text(
                            text = "Allergens",
                            color = Color.Gray,
                            modifier = Modifier
                                .padding(top = 24.dp)
                        )
                        Text(
                            text = item.itemAllergens?.joinToString(separator = ", ") ?: "",
                            modifier = Modifier
                                .padding(top = 6.dp)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(20.dp))
                                .background(Color(0xFFFFA500).copy(alpha = 0.4f))
                                .padding(start = 10.dp, end = 10.dp, top = 4.dp, bottom = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetailsViewPreview() {
    val mockDrink = OfferedDrink(
        name = "Long Island Iced Tea",
        description = "A refreshing cocktail made with vodka, rum, gin, tequila, and cola.",
        price = 7.99,
        allergens = listOf("alcohol"),
        img = ""
    )

    DetailsView(
        item = mockDrink,
        navigateBack = { }
    )
}