package com.daviddev.reungbox.ui.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerAnimation(
    modifier: Modifier = Modifier,
    cornerRadius: Int = 8
) {
    val gradientColors = listOf(
        MaterialTheme.colorScheme.surface.copy(alpha = 0.3f),
        MaterialTheme.colorScheme.surface.copy(alpha = 0.1f),
        MaterialTheme.colorScheme.surface.copy(alpha = 0.3f)
    )

    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f, // slide distance
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            )
        )
    )

    val brush = Brush.linearGradient(
        colors = gradientColors,
        start = Offset(translateAnim - 1000f, 0f),
        end = Offset(translateAnim, 0f)
    )

    Spacer(
        modifier = modifier
            .background(brush, shape = RoundedCornerShape(cornerRadius.dp))
    )
}