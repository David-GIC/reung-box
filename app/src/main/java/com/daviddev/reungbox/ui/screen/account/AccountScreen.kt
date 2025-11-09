package com.daviddev.reungbox.ui.screen.account

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.daviddev.reungbox.ui.components.CustomElevatedButton
import com.daviddev.reungbox.ui.components.SpaceWidth
import com.daviddev.reungbox.ui.screen.auth.AuthViewModel
import com.daviddev.reungbox.ui.theme.BackgroundColor
import com.daviddev.reungbox.ui.theme.OpenSanFontFamily
import com.daviddev.reungbox.ui.theme.PrimaryColor
import com.daviddev.reungbox.ui.theme.SecondaryColor
import com.daviddev.reungbox.ui.theme.WhiteColor
import com.daviddev.reungbox.utils.ImageAsset
import com.daviddev.reungbox.utils.Routes
import com.daviddev.reungbox.utils.getScreenWidth
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(bottomNavController: NavHostController, screenNavController: NavHostController) {
    val deviceWidth = getScreenWidth()
    val authViewModel : AuthViewModel = viewModel()

    Scaffold(modifier = Modifier.fillMaxSize().padding(0.dp), containerColor = BackgroundColor, topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = BackgroundColor,
                titleContentColor = WhiteColor,
            ),
            title = {
                Text("Account", fontFamily = OpenSanFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 20.sp,modifier = Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
            },
        )
    }){
        Row (
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            if(authViewModel.isLoggedIn) {
                Text("Logged as ${authViewModel.auth.currentUser?.email}", fontWeight = FontWeight.W500, fontFamily = OpenSanFontFamily, fontSize = 16.sp, color = WhiteColor)
                SpaceWidth(16)
                CustomElevatedButton(
                    modifier = Modifier
                        .width(deviceWidth / 2)
                        .height(39.dp),
                    containerColor = PrimaryColor,
                    onClick = {
                        authViewModel.onLogout()
                    },
                    icon = null,
                    text = "Logout"
                )
            }else{
                CustomElevatedButton(
                    modifier = Modifier
                        .width(deviceWidth / 2)
                        .height(39.dp),
                    containerColor = PrimaryColor,
                    onClick = {
                        screenNavController.navigate(Routes.LoginScreen)
                    },
                    icon = null,
                    text = "Login"
                )

                SpaceWidth(16)

                CustomElevatedButton(
                    modifier = Modifier
                        .width(deviceWidth / 2)
                        .height(39.dp),
                    containerColor = SecondaryColor,
                    onClick = {
                        screenNavController.navigate(Routes.SignUpScreen)
                    },
                    icon = null,
                    text = "Sign Up"
                )
            }
        }
    }
}