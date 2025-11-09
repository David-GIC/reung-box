package com.daviddev.reungbox.ui.screen.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.daviddev.reungbox.ui.theme.BackgroundColor
import com.daviddev.reungbox.ui.theme.OpenSanFontFamily
import com.daviddev.reungbox.ui.theme.WhiteColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(bottomNavController: NavHostController, screenNavController: NavHostController) {
    Scaffold(modifier = Modifier.fillMaxSize().padding(0.dp), containerColor = BackgroundColor, topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = BackgroundColor,
                titleContentColor = WhiteColor,
            ),
            title = {
                Text("Search", fontFamily = OpenSanFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 20.sp,modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
            },
        )
    }){

    }
}