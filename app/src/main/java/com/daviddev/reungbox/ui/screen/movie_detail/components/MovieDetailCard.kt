package com.daviddev.reungbox.ui.screen.movie_detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.daviddev.reungbox.R
import com.daviddev.reungbox.domain.models.Movie
import com.daviddev.reungbox.ui.components.SpaceHeight
import com.daviddev.reungbox.ui.components.SpaceWidth
import com.daviddev.reungbox.ui.theme.GrayColor
import com.daviddev.reungbox.ui.theme.OpenSanFontFamily
import com.daviddev.reungbox.ui.theme.SecondaryColor
import com.daviddev.reungbox.ui.theme.WhiteColor
import com.daviddev.reungbox.utils.ImageAsset

@Composable
fun MovieDetailCard(movie: Movie?){
    Box(
        modifier = Modifier.fillMaxWidth().height(180.dp).clip(RoundedCornerShape(10.dp)).background(
            SecondaryColor
        )
    ) {
        Row (
            modifier = Modifier.padding(8.dp)
        ){
            AsyncImage(
                model = movie?.primaryImage,
                contentDescription = "Movie Image",
                contentScale = ContentScale.FillBounds,
                placeholder = painterResource(R.drawable.ic_logo),
                modifier = Modifier.width(170.dp).height(170.dp).clip(RoundedCornerShape(10.dp))
            )
            SpaceWidth(10)
            Column (
                verticalArrangement = Arrangement.Top
            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text("Trailer", fontFamily = OpenSanFontFamily, fontSize = 18.sp, fontWeight = FontWeight.W600, color = WhiteColor)
                    Icon(painterResource(ImageAsset.DownloadIcon),
                        tint = WhiteColor,
                        contentDescription = "", modifier = Modifier.width(20.dp).height(20.dp))
                }
                SpaceHeight(10)
                Text(movie?.description ?: "", fontFamily = OpenSanFontFamily, fontSize = 14.sp, color = WhiteColor, textAlign = TextAlign.Start)
            }
        }
    }
}