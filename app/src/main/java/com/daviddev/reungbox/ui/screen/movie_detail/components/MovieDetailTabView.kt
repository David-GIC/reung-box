package com.daviddev.reungbox.ui.screen.movie_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.daviddev.reungbox.domain.models.Movie
import com.daviddev.reungbox.ui.components.SpaceHeight
import com.daviddev.reungbox.ui.theme.BackgroundColor
import com.daviddev.reungbox.ui.theme.GrayColor
import com.daviddev.reungbox.ui.theme.PrimaryColor
import kotlinx.coroutines.coroutineScope

@Composable
fun MovieTabView(movie: Movie?) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Episode", "Similar", "About")
    val state = rememberPagerState(initialPage = 0) { 3 }

    Column (
        modifier = Modifier.background(BackgroundColor).fillMaxSize()
    ) {
        TabRow(
            contentColor = BackgroundColor,
            containerColor = BackgroundColor,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = PrimaryColor
                )
            },
            selectedTabIndex = selectedTabIndex) {
            tabs.forEachIndexed { index, tabItem ->
                Tab(
                    selectedContentColor = PrimaryColor,
                    unselectedContentColor = GrayColor,
                    selected = index == selectedTabIndex,
                    onClick = { selectedTabIndex = index },
                    text = { Text(tabItem) },
                )
            }
        }

        HorizontalPager(
            state = state
        ) { page ->
            when (page) {
                0 -> MovieDetailList(movie)
                1 -> MovieDetailList(movie)
                2 -> MovieDetailList(movie)
            }
        }
    }
}

@Composable
fun MovieDetailList(movie: Movie?){
    Column(
        modifier = Modifier.fillMaxSize()
    ){
        SpaceHeight(20)
        for (i in 1..10) {
            MovieDetailCard(movie)
            SpaceHeight(10)
        }
    }
}