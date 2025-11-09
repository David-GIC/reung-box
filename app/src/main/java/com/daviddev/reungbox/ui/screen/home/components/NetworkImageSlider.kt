package com.daviddev.reungbox.ui.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.daviddev.reungbox.domain.models.Movie
import com.daviddev.reungbox.ui.theme.GrayColor
import com.daviddev.reungbox.ui.theme.PrimaryColor
import com.daviddev.reungbox.utils.getScreenHeight
import kotlinx.coroutines.launch
import com.daviddev.reungbox.R
import kotlinx.coroutines.delay

@Composable
fun NetworkImageSlider(imageUrls: List<Movie>,
                       onClick: ((Movie) -> Unit)? = null) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val deviceHeight = getScreenHeight()

    // Auto-slide effect
    LaunchedEffect(key1 = listState) {
        while (true) {
            delay(3000) // Slide every 3 seconds
            val nextIndex = (listState.firstVisibleItemIndex + 1) % imageUrls.size
            coroutineScope.launch {
                listState.animateScrollToItem(nextIndex)
            }
        }
    }

    Box(modifier = Modifier.fillMaxWidth().height(deviceHeight / 2)) {
        LazyRow(
            state = listState,
            modifier = Modifier.fillMaxSize()
        ) {
            items(imageUrls.size) { index ->
                AsyncImage(
                    model = imageUrls[index].primaryImage,
                    contentDescription = "Slider Image",
                    contentScale = ContentScale.FillWidth,
                    placeholder = painterResource(R.drawable.ic_logo),
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .fillMaxHeight().clickable {
                            onClick?.invoke(imageUrls[index])
                        }
                )
            }
        }

        // Dots Indicator
        Row(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter).padding(bottom = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            imageUrls.forEachIndexed { index, _ ->
                val isSelected = listState.firstVisibleItemIndex == index
                Box(
                    modifier = Modifier
                        .width(if (isSelected) 20.dp else 8.dp).height(8.dp)
                        .clip(RoundedCornerShape(50))
                        .background(if (isSelected) PrimaryColor else GrayColor)
                        .clickable {
                            coroutineScope.launch {
                                listState.animateScrollToItem(index)
                            }
                        }
                )
                Spacer(modifier = Modifier.width(6.dp))
            }
        }
    }
}
