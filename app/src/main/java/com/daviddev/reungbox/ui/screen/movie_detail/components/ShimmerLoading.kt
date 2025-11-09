package com.daviddev.reungbox.ui.screen.movie_detail.components


import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daviddev.reungbox.ui.components.ShimmerAnimation
import com.daviddev.reungbox.ui.components.SpaceHeight
import com.daviddev.reungbox.ui.components.SpaceWidth
import com.daviddev.reungbox.ui.screen.home.components.HomeShimmerLoading
import com.daviddev.reungbox.utils.getScreenHeight
import com.daviddev.reungbox.utils.getScreenWidth

@Composable
fun MovieDetailShimmerLoading(){
    val deviceHeight = getScreenHeight()
    val deviceWidth = getScreenWidth() - 30.dp

    Column {
        Box(modifier = Modifier.fillMaxWidth().height(deviceHeight / 2)) {
            ShimmerAnimation(
                modifier = Modifier
                    .fillMaxSize(),
                cornerRadius = 0
            )
        }
        SpaceHeight(10)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ShimmerAnimation(
                modifier = Modifier
                    .size(width = 200.dp, height = 30.dp),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ShimmerAnimation(
                modifier = Modifier
                    .size(width = 200.dp, height = 15.dp),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ShimmerAnimation(
                modifier = Modifier
                    .size(width = deviceWidth / 2 , height = 40.dp),
                cornerRadius = 20
            )
            ShimmerAnimation(
                modifier = Modifier
                    .size(width = deviceWidth / 2 , height = 40.dp),
                cornerRadius = 20
            )
        }
        SpaceHeight(10)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ShimmerAnimation(
                modifier = Modifier
                    .size(width = deviceWidth, height = 15.dp),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ShimmerAnimation(
                modifier = Modifier
                    .size(width = deviceWidth, height = 15.dp),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            ShimmerAnimation(
                modifier = Modifier
                    .size(width = deviceWidth, height = 15.dp),
            )
        }
        SpaceHeight(10)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ShimmerAnimation(
                modifier = Modifier
                    .size(width = 100.dp, height = 30.dp),
                cornerRadius = 0
            )
            ShimmerAnimation(
                modifier = Modifier
                    .size(width = 100.dp, height = 30.dp),
                cornerRadius = 0
            )

            ShimmerAnimation(
                modifier = Modifier
                    .size(width = 100.dp, height = 30.dp),
                cornerRadius = 0
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ShimmerAnimation(
                modifier = Modifier
                    .size(width = deviceWidth, height = 180.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview3() {
    MovieDetailShimmerLoading()
}
