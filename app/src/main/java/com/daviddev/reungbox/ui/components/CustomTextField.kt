package com.daviddev.reungbox.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.daviddev.reungbox.ui.theme.BackgroundColor
import com.daviddev.reungbox.ui.theme.GrayColor
import com.daviddev.reungbox.ui.theme.PrimaryColor
import com.daviddev.reungbox.ui.theme.WhiteColor


@Composable
fun CustomTextField(
    passwordVisible: Boolean,
    modifier: Modifier = Modifier,
    text: String,
    onTextChange: (String) -> Unit,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    placeholderText: String = "Placeholder",
    fontSize: TextUnit = MaterialTheme.typography.bodyLarge.fontSize
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            placeholderText, fontSize = 15.sp, fontWeight = FontWeight.Normal, color = WhiteColor,             modifier = Modifier.padding(horizontal = 35.dp),
        )
        BasicTextField(
            modifier = Modifier
                .background(
                    BackgroundColor,
                    RoundedCornerShape(percent = 50)
                )
                .padding(horizontal = 30.dp, vertical = 7.dp)
                .border(
                    2.dp,
                    color = WhiteColor,
                    RoundedCornerShape(percent = 50)
                )
                .fillMaxWidth(),
            value = text,
            onValueChange = onTextChange,
            singleLine = true,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            cursorBrush = SolidColor(PrimaryColor),
            textStyle = LocalTextStyle.current.copy(
                color = WhiteColor,
                fontSize = fontSize
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (leadingIcon != null) leadingIcon()

                    Box(
                        Modifier
                            .weight(1f)
                            .padding(16.dp)
                    ) {
                        if (text.isEmpty()) {
                            Text(
                                text = "",
                                style = LocalTextStyle.current.copy(
                                    color = WhiteColor,
                                    fontSize = fontSize
                                )
                            )
                        }
                        innerTextField()
                    }
                    if (trailingIcon != null) trailingIcon()
                }
            }
        )
    }
}