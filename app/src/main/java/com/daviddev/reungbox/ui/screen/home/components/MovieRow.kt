package com.daviddev.reungbox.ui.screen.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.daviddev.reungbox.domain.models.Movie
import com.daviddev.reungbox.ui.theme.OpenSanFontFamily
import com.daviddev.reungbox.ui.theme.WhiteColor
import com.daviddev.reungbox.R

@Composable
fun MovieRow(
    title: String,
    movies: List<Movie>,
    onSeeMoreClick: (() -> Unit)? = null,
    onMovieClick: ((Movie) -> Unit)? = null
) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)) {

        // --- Title Row ---
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                color = WhiteColor,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                fontFamily = OpenSanFontFamily
            )

            Text(
                text = "See more",
                color = WhiteColor,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                fontFamily = OpenSanFontFamily,
                modifier = Modifier.clickable { onSeeMoreClick?.invoke() }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // --- Movie List Row ---
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(movies) { movie ->
                MovieItem(
                    movie = movie,
                    onClick = { onMovieClick?.invoke(movie) }
                )
            }
        }
    }
}

@Composable
fun MovieItem(
    movie: Movie,
    onClick: ((Movie) -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .width(140.dp).height(180.dp)
            .clickable { onClick?.invoke(movie) }
    ) {
        AsyncImage(
            model = movie.primaryImage, // Adjust field name to your model
            contentDescription = movie.originalTitle,
            placeholder = painterResource(R.drawable.ic_logo),
            error = painterResource(R.drawable.ic_logo),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth().clip(RoundedCornerShape(10.dp))

        )
    }
}