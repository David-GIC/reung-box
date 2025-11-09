package com.daviddev.reungbox.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.daviddev.reungbox.ui.theme.OpenSanFontFamily
import com.daviddev.reungbox.ui.theme.PrimaryColor
import com.daviddev.reungbox.utils.ImageAsset

@Composable
fun CustomElevatedButton(modifier: Modifier, containerColor: Color, onClick: () -> Unit, icon: Int?, text: String){
    ElevatedButton(
        modifier = modifier,
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = containerColor, // Background color of the button
            contentColor = Color.White, // Color of the text/content inside the button
            disabledContainerColor = Color.Gray, // Background color when disabled
            disabledContentColor = Color.LightGray // Content color when disabled
        ),
        onClick = onClick,
        ) {
        if(icon != null ){
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "",
                modifier = Modifier.size(width = 16.dp, height = 16.dp)
            )
            SpaceWidth(8)
        }
        Text(text, fontWeight = FontWeight.SemiBold,
            fontFamily = OpenSanFontFamily,)
    }
}