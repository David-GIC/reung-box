package com.daviddev.reungbox.ui.screen.movie_detail

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.daviddev.reungbox.ui.components.CustomCircleIconButton
import com.daviddev.reungbox.utils.getScreenHeight
import com.daviddev.reungbox.ui.components.SpaceWidth
import com.daviddev.reungbox.utils.ImageAsset
import com.daviddev.reungbox.R
import com.daviddev.reungbox.ui.components.CustomElevatedButton
import com.daviddev.reungbox.ui.components.CustomErrorComponent
import com.daviddev.reungbox.ui.components.SpaceHeight
import com.daviddev.reungbox.ui.screen.movie_detail.components.MovieDetailShimmerLoading
import com.daviddev.reungbox.ui.screen.movie_detail.components.MovieTabView
import com.daviddev.reungbox.ui.theme.GrayColor
import com.daviddev.reungbox.ui.theme.OpenSanFontFamily
import com.daviddev.reungbox.ui.theme.PrimaryColor
import com.daviddev.reungbox.ui.theme.SecondaryColor
import com.daviddev.reungbox.ui.theme.WhiteColor
import com.daviddev.reungbox.utils.Routes
import com.daviddev.reungbox.utils.getScreenWidth
import com.daviddev.reungbox.utils.showToast

@Composable
fun MovieDetail(navController: NavHostController, id: String) {
    val deviceHeight = getScreenHeight()
    val deviceWidth = getScreenWidth() - 42.dp
    val movieDetailViewModel: MovieDetailViewModel = viewModel();
    val state by movieDetailViewModel.uiState.collectAsState()
    val scrollState: ScrollState = rememberScrollState()

    if (state.isLoading) {
        MovieDetailShimmerLoading()
    } else if (state.error != null) {
        CustomErrorComponent("Error: ${state.error}")
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing),
            contentPadding = PaddingValues(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Box(modifier = Modifier.fillMaxWidth().height(deviceHeight / 2)) {
                    AsyncImage(
                        model = state.movie?.primaryImage,
                        contentDescription = state.movie?.originalTitle,
                        contentScale = ContentScale.FillBounds,
                        placeholder = painterResource(R.drawable.ic_logo),
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        CustomCircleIconButton(ImageAsset.BackIcon, onClick = {
                            navController.popBackStack()
                        })
                        Row {
                            CustomCircleIconButton(ImageAsset.SavedIcon, onClick = {})
                            SpaceWidth(10)
                            CustomCircleIconButton(ImageAsset.ShareIcon, onClick = {})
                        }
                    }
                }
            }

            item {
                SpaceHeight(16)
                Text(state.movie?.originalTitle ?: "", fontFamily = OpenSanFontFamily, fontSize = 18.sp, color = WhiteColor, fontWeight = FontWeight.W600)
                SubTitleTitleText("${state.movie?.startYear} - ${state.movie?.genres} - ${state.movie?.runtimeMinutes} min")
                SpaceHeight(16)
                Row (
                    horizontalArrangement = Arrangement.SpaceAround
                ){

                    CustomElevatedButton(
                        modifier = Modifier
                            .width(deviceWidth / 2)
                            .height(39.dp),
                        containerColor = PrimaryColor,
                        onClick = {},
                        icon = ImageAsset.PlayIcon,
                        text = "Play"
                    )

                    SpaceWidth(16)

                    CustomElevatedButton(
                        modifier = Modifier
                            .width(deviceWidth / 2)
                            .height(39.dp),
                        containerColor = SecondaryColor,
                        onClick = {},
                        icon = ImageAsset.DownloadIcon,
                        text = "Download"
                    )
                }
                SpaceHeight(16)
                SubTitleTitleText(state.movie?.description ?: "")
                SpaceHeight(16)
                MovieTabView(state.movie)
            }
        }
    }
}

@Composable
fun SubTitleTitleText(text: String){
    Text(text, fontFamily = OpenSanFontFamily, fontSize = 14.sp, color = GrayColor, textAlign = TextAlign.Center)
}

//@Preview(showBackground = true)
//@Composable
//fun MyAppPreview3() {
//    MovieTabView()
//}