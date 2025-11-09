package com.daviddev.reungbox.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.daviddev.reungbox.R
import com.daviddev.reungbox.data.local.CustomSharePreference
import com.daviddev.reungbox.ui.components.SpaceHeight
import com.daviddev.reungbox.ui.screen.home.components.HomeShimmerLoading
import com.daviddev.reungbox.ui.theme.BackgroundColor
import com.daviddev.reungbox.ui.theme.WhiteColor
import com.daviddev.reungbox.utils.ImageAsset
import com.daviddev.reungbox.utils.Routes
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {
    val context = LocalContext.current
    // Simulate loading
    LaunchedEffect(Unit) {
        delay(1000)
        val isOnboard = CustomSharePreference.getOnboardState(context)
        if(isOnboard){
            navController.navigate(Routes.RootBottomNavigationBar) {
                popUpTo(Routes.SplashScreen) { inclusive = true } // remove splash from back stack
            }
        }else{
            CustomSharePreference.saveOnboardState(context, true);
            navController.navigate(Routes.WelcomeScreen) {
                popUpTo(Routes.SplashScreen) { inclusive = true } // remove splash from back stack
            }
        }

    }

    SplashWithProgress()
}

@Composable
fun SplashWithProgress() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(painterResource(R.drawable.ic_logo), contentDescription = "", modifier = Modifier.size(100.dp).clip(
                RoundedCornerShape(10)
            ))
            SpaceHeight(16)
            Text("Loading ...", style = MaterialTheme.typography.titleMedium, color = WhiteColor)
            SpaceHeight(16)
            LinearProgressIndicator(modifier = Modifier.width(200.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview3() {
    SplashWithProgress()
}