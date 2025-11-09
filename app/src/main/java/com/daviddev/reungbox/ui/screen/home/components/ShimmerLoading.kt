package com.daviddev.reungbox.ui.screen.home.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.daviddev.reungbox.ui.components.ShimmerAnimation
import com.daviddev.reungbox.utils.getScreenHeight

@Composable
fun HomeShimmerLoading(){
    val deviceHeight = getScreenHeight()
    Column {
        Box(modifier = Modifier.fillMaxWidth().height(deviceHeight / 2)) {
            ShimmerAnimation(
                modifier = Modifier
                    .fillMaxSize(),
                        cornerRadius = 0
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(5) {
                ShimmerAnimation(
                    modifier = Modifier
                        .size(width = 100.dp, height = 30.dp),
                    cornerRadius = 30
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
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
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(5) {
                ShimmerAnimation(
                    modifier = Modifier
                        .size(width = 120.dp, height = 180.dp)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
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
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(5) {
                ShimmerAnimation(
                    modifier = Modifier
                        .size(width = 120.dp, height = 180.dp)
                )
            }
        }
    }
}
