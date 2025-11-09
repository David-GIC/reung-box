package com.daviddev.reungbox.ui.screen.welcome

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import com.daviddev.reungbox.R
import com.daviddev.reungbox.ui.theme.BackgroundColor
import com.daviddev.reungbox.ui.theme.OpenSanFontFamily
import com.daviddev.reungbox.ui.theme.PrimaryColor
import com.daviddev.reungbox.ui.theme.WhiteColor
import com.daviddev.reungbox.utils.ConstantValue
import com.daviddev.reungbox.utils.Routes
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun WelcomeScreen(navController: NavHostController) {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    Scaffold(modifier = Modifier.fillMaxSize().padding(WindowInsets.systemBars.asPaddingValues()), containerColor = BackgroundColor, topBar = {}) {
        Column (verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()){
            Image(
                painter = painterResource(R.drawable.ic_welcome),
                contentDescription = "Hello Image",
                modifier = Modifier.padding(30.dp)
            )
            Text(
                text = "HELLO",
                fontWeight = FontWeight.Bold,
                fontFamily = OpenSanFontFamily,
                color = PrimaryColor,
                fontSize = 25.sp,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Welcome to ${ConstantValue.appName}! \n Your gateway to great movies and shows. \nHit Browse and enjoy the ride!",
                fontWeight = FontWeight.Normal,
                fontFamily = OpenSanFontFamily,
                color = WhiteColor,
                fontSize = 17.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(40.dp))

            ElevatedButton(
                modifier = Modifier.width(200.dp).height(39.dp),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = PrimaryColor, // Background color of the button
                    contentColor = Color.White, // Color of the text/content inside the button
                    disabledContainerColor = Color.Gray, // Background color when disabled
                    disabledContentColor = Color.LightGray // Content color when disabled
                ),
                onClick = {
                    navController.navigate(Routes.RootBottomNavigationBar) {
                        popUpTo(Routes.WelcomeScreen) { inclusive = true } // remove splash from back stack
                    }
                },

                ) {
                Text("Browse", fontWeight = FontWeight.SemiBold,
                    fontFamily = OpenSanFontFamily,)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun MyAppPreview() {
//    WelcomeScreen()
//}