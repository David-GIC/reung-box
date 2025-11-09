package com.daviddev.reungbox.ui.screen.auth.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.daviddev.reungbox.ui.screen.auth.AuthViewModel
import com.daviddev.reungbox.R
import com.daviddev.reungbox.ui.components.CustomCircleIconButton
import com.daviddev.reungbox.ui.theme.OpenSanFontFamily
import com.daviddev.reungbox.ui.theme.PrimaryColor
import com.daviddev.reungbox.ui.theme.WhiteColor
import com.daviddev.reungbox.utils.Routes
import com.daviddev.reungbox.utils.showToast
import com.daviddev.reungbox.ui.components.CustomTextField
import com.daviddev.reungbox.ui.screen.splash.SplashWithProgress
import com.daviddev.reungbox.ui.theme.BackgroundColor
import com.daviddev.reungbox.utils.ConstantValue
import com.daviddev.reungbox.utils.ImageAsset

@Composable
fun LoginScreen(navController: NavHostController) {

    val context = LocalContext.current

    val authViewModel : AuthViewModel = viewModel();

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .safeDrawingPadding(), containerColor = BackgroundColor
    ) {
        Column (verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()){
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                CustomCircleIconButton(ImageAsset.BackIcon, onClick = {
                    navController.popBackStack()
                })
            }
            Image(
                painter = painterResource(R.drawable.ic_login),
                contentDescription = "Hello Image",
                modifier = Modifier.padding(horizontal = 40.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "LOGIN",
                fontWeight = FontWeight.Bold,
                fontFamily = OpenSanFontFamily,
                color = PrimaryColor,
                fontSize = 25.sp,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Welcome Back to ${ConstantValue.appName}",
                fontWeight = FontWeight.Normal,
                fontFamily = OpenSanFontFamily,
                color = WhiteColor,
                fontSize = 17.sp,
            )
            Spacer(modifier = Modifier.height(40.dp))

            CustomTextField(
                text = authViewModel.emailInput,
                onTextChange = { authViewModel.emailInput = it},
                fontSize = 16.sp,
                placeholderText = "Email",
                passwordVisible = true
            )
            Spacer(modifier = Modifier.height(10.dp))
            CustomTextField(
                text = authViewModel.passwordInput,
                onTextChange = { authViewModel.passwordInput = it},
                trailingIcon = {
                    IconButton(
                        onClick = {
                            authViewModel.passwordVisible = !authViewModel.passwordVisible
                        }
                    ) {
                        Icon(
                            painter = painterResource(if (authViewModel.passwordVisible) R.drawable.ic_invisible else R.drawable.ic_visibile),
                            null,
                            tint = WhiteColor,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                fontSize = 16.sp,
                placeholderText = "Password",
                passwordVisible = authViewModel.passwordVisible
            )
            Column (
                modifier = Modifier
                    .fillMaxWidth().padding(horizontal = 8.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ){

                Text(
                    "Forget Password",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = WhiteColor,
                    modifier = Modifier.padding(horizontal = 35.dp)
                        .clickable {
                            Log.d("Forget Password", "Forget Password clicked")
                        },
                )
            }
            Spacer(modifier = Modifier.height(30.dp))

            ElevatedButton(
                modifier = Modifier
                    .width(200.dp)
                    .height(39.dp),
                enabled = !authViewModel.isLoading,
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = PrimaryColor, // Background color of the button
                    contentColor = Color.White, // Color of the text/content inside the button
                    disabledContainerColor = Color.Gray, // Background color when disabled
                    disabledContentColor = Color.LightGray // Content color when disabled
                ),
                onClick = {
                    authViewModel.login(context,
                        onResult =  {
                        if(!it.isError) {
                            context.showToast(it.message as String)
                            navController.navigate(Routes.RootBottomNavigationBar) {
                                popUpTo(Routes.LoginScreen) { inclusive = true } // remove login from back stack
                            }
                        }else{
                                context.showToast("Login failed | ${it.message}")
                        }
                    })
                },

                ) {
                if (authViewModel.isLoading) {
                    CircularProgressIndicator(
                        color = WhiteColor,
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("Login", fontWeight = FontWeight.SemiBold,
                        fontFamily = OpenSanFontFamily,)
                }

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyAppPreview3() {
    CustomTextField(
        text = "",
        onTextChange = { },
        trailingIcon = {
            IconButton(
                onClick = {
                }
            ) {
                Icon(
                    painter = painterResource(if (true) R.drawable.ic_invisible else R.drawable.ic_visibile),
                    null,
                    tint = WhiteColor,
                    modifier = Modifier.size(30.dp)
                )
            }
        },
        fontSize = 16.sp,
        placeholderText = "Password",
        passwordVisible = true
    )
}