package com.daviddev.reungbox.ui.screen.home

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.daviddev.reungbox.R
import com.daviddev.reungbox.ui.components.CustomCircleIconButton
import com.daviddev.reungbox.ui.components.CustomErrorComponent
import com.daviddev.reungbox.ui.screen.home.components.HomeShimmerLoading
import com.daviddev.reungbox.ui.screen.home.components.MovieRow
import com.daviddev.reungbox.ui.screen.home.components.NetworkImageSlider
import com.daviddev.reungbox.ui.screen.home.components.SelectableChipRow
import com.daviddev.reungbox.ui.theme.BackgroundColor
import kotlinx.serialization.encodeToString

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(bottomNavController: NavHostController, screenNavController: NavHostController) {

    val homeViewModel : HomeViewModel = viewModel();
    val state by homeViewModel.uiState.collectAsState()
    val scrollState = rememberLazyListState()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    Scaffold(modifier = Modifier.fillMaxSize().padding(0.dp), containerColor = BackgroundColor, topBar = {

    }){
        if (state.isLoading) {
            HomeShimmerLoading()
        } else if (state.error != null) {
            CustomErrorComponent("Error: ${state.error}")
        } else {
            LazyColumn (
                state = scrollState,
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeDrawing),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                item {
                    NetworkImageSlider(state.banner, onClick = {
                        screenNavController.navigate("MovieDetailScreen/${it.id}")
                    })
                    Spacer(modifier = Modifier.height(10.dp))
                }
                stickyHeader {
                    Surface(
                        color = BackgroundColor,
                        shadowElevation = 4.dp,
                    ) {
                        SelectableChipRow(homeViewModel.genres, onSelected = {
                            homeViewModel.onUpdateGenres(it)
                            homeViewModel.fetchAllData()
                        })
                    }

                }
                item {
                    MovieRow(title = "Movies", movies = state.movies, onMovieClick = {
                        screenNavController.navigate("MovieDetailScreen/${it.id}")
                    })
                    MovieRow(title = "Popular", movies = state.popular, onMovieClick = {
                        screenNavController.navigate("MovieDetailScreen/${it.id}")
                    })
                    MovieRow(title = "Top 250 Movies", movies = state.top250Movies, onMovieClick = {
                        screenNavController.navigate("MovieDetailScreen/${it.id}")
                    })
                    MovieRow(title = "Top Rating Movies", movies = state.topRateMovie, onMovieClick = {
                        screenNavController.navigate("MovieDetailScreen/${it.id}")
                    })
                    Spacer(modifier = Modifier.height(140.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview3() {
    HomeShimmerLoading()
}

