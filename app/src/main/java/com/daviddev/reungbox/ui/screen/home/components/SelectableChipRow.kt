package com.daviddev.reungbox.ui.screen.home.components
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.daviddev.reungbox.ui.screen.home.HomeViewModel
import com.daviddev.reungbox.ui.theme.OpenSanFontFamily
import com.daviddev.reungbox.ui.theme.PrimaryColor
import com.daviddev.reungbox.ui.theme.SecondaryColor

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SelectableChipRow(
    items: List<String>,
    modifier: Modifier = Modifier,
    onSelected: (String) -> Unit
) {
    val homeViewModel : HomeViewModel = viewModel()
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(items) { item ->
            val isSelected = item == homeViewModel.genresState.value
            Box(
                modifier = Modifier
                    .background(
                        color = if (isSelected) PrimaryColor else SecondaryColor,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .clickable {
                        onSelected(item)
                    }
                    .padding(horizontal = 20.dp, vertical = 8.dp)
            ) {
                Text(
                    text = item,
                    color = Color.White,
                    fontFamily = OpenSanFontFamily,
                    fontSize = 16.sp,
                )
            }
        }
    }
}
