package com.daviddev.reungbox.ui.components

import android.widget.Button
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.daviddev.reungbox.R
import com.daviddev.reungbox.ui.theme.WhiteColor

@Composable
fun CustomCircleIconButton(
    @DrawableRes iconResId: Int, // Resource ID of the social media icon
    onClick: () -> Unit,
) {
    Button (
        onClick = onClick,
        modifier = Modifier.size(39.dp).clip(CircleShape),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(contentColor =  WhiteColor, containerColor = WhiteColor.copy(0.1f))
    ) {
        Icon(
            painter = painterResource(id = iconResId),
            contentDescription = "",
            modifier = Modifier.size(width = 16.dp, height = 16.dp)
            )
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview2() {
    CustomCircleIconButton(R.drawable.ic_saved, onClick = {})
}