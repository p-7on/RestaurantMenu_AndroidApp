package com.example.menuapp.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.menuapp.ViewModel.AppStateEnum

@Composable
fun ErrorView(state: AppStateEnum) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .size(140.dp)
                .graphicsLayer {
                    shadowElevation = 10.dp.toPx()
                    shape = RoundedCornerShape(10.dp)
                    clip = true
                }
                .background(Color.White)
                .padding(16.dp)
        ) {
            Icon(
                Icons.Default.WifiOff, "WifiOff_Icon",
                tint = Color.Red,
                modifier = Modifier
                    .align(Center)
                    .size(100.dp)
            )
        }

        Text(text = state.toString(),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 25.dp, start = 16.dp, end = 16.dp)
            )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ErrorViewPreview() {
    ErrorView(AppStateEnum.failure("Invalid URL"))
}