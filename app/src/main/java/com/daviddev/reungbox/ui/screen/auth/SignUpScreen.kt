package com.example.my_application.signup

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.daviddev.reungbox.R
import com.daviddev.reungbox.ui.components.CustomCircleIconButton
import com.daviddev.reungbox.ui.theme.OpenSanFontFamily
import com.daviddev.reungbox.ui.theme.PrimaryColor
import com.daviddev.reungbox.ui.theme.WhiteColor
import com.daviddev.reungbox.utils.Routes
import com.daviddev.reungbox.utils.showToast
import com.daviddev.reungbox.ui.components.CustomTextField
import com.daviddev.reungbox.ui.screen.auth.AuthViewModel
import com.daviddev.reungbox.ui.theme.BackgroundColor
import com.daviddev.reungbox.utils.ConstantValue
import com.daviddev.reungbox.utils.ImageAsset

@Composable
fun SignUpScreen(navController: NavHostController) {

    val scrollState = rememberScrollState()

    val authViewModel : AuthViewModel = viewModel();

    val context = LocalContext.current

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    Scaffold(modifier = Modifier
        .fillMaxSize()
        .safeDrawingPadding(), containerColor = BackgroundColor
    ) {
        Column (verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize().verticalScroll(scrollState)){
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
                text = "SIGN UP",
                fontWeight = FontWeight.Bold,
                fontFamily = OpenSanFontFamily,
                color = PrimaryColor,
                fontSize = 25.sp,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Sign Up with ${ConstantValue.appName}",
                fontWeight = FontWeight.Normal,
                fontFamily = OpenSanFontFamily,
                color =WhiteColor,
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
                text = authViewModel.usernameInput,
                onTextChange = { authViewModel.usernameInput = it},
                fontSize = 16.sp,
                placeholderText = "Username",
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
                            tint = LocalContentColor.current.copy(alpha = 0.3f),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                fontSize = 16.sp,
                placeholderText = "Password",
                passwordVisible = authViewModel.passwordVisible
            )
            Spacer(modifier = Modifier.height(10.dp))
            CustomTextField(
                text = authViewModel.confirmPasswordInput,
                onTextChange = { authViewModel.confirmPasswordInput = it},
                trailingIcon = {
                    IconButton(
                        onClick = {
                            authViewModel.confirmPasswordVisible = !authViewModel.confirmPasswordVisible
                        }
                    ) {
                        Icon(
                            painter = painterResource(if (authViewModel.confirmPasswordVisible) R.drawable.ic_invisible else R.drawable.ic_visibile),
                            null,
                            tint = LocalContentColor.current.copy(alpha = 0.3f),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                fontSize = 16.sp,
                placeholderText = "Confirm Password",
                passwordVisible = authViewModel.confirmPasswordVisible
            )
            Spacer(modifier = Modifier.height(30.dp))

            ElevatedButton(
                modifier = Modifier
                    .width(200.dp)
                    .height(39.dp),
                colors = ButtonDefaults.elevatedButtonColors(
                    containerColor = PrimaryColor, // Background color of the button
                    contentColor = Color.White, // Color of the text/content inside the button
                    disabledContainerColor = Color.Gray, // Background color when disabled
                    disabledContentColor = Color.LightGray // Content color when disabled
                ),
                onClick = {
                    authViewModel.register(context,
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
                Text("Sign Up", fontWeight = FontWeight.SemiBold,
                    fontFamily = OpenSanFontFamily,)
            }

            Spacer(modifier = Modifier.height(100.dp))

        }
    }
}