package com.daviddev.reungbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.daviddev.reungbox.ui.screen.auth.login.LoginScreen
import com.daviddev.reungbox.ui.screen.movie_detail.MovieDetail
import com.daviddev.reungbox.ui.screen.movie_detail.MovieDetailViewModel
import com.daviddev.reungbox.ui.screen.root.RootBottomNavBarScreen
import com.daviddev.reungbox.ui.screen.splash.SplashScreen
import com.daviddev.reungbox.ui.screen.welcome.WelcomeScreen
import com.daviddev.reungbox.utils.Routes
import com.example.my_application.signup.SignUpScreen
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(scrim = Color.Transparent.toArgb()),
            navigationBarStyle = SystemBarStyle.dark(
                scrim = Color.Transparent.toArgb()
            )
        )

        setContent {
            AppNavigator()
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.SplashScreen) {
        composable(Routes.SplashScreen) { SplashScreen(navController) }
        composable(Routes.WelcomeScreen) { WelcomeScreen(navController) }
        composable(Routes.LoginScreen) { LoginScreen(navController) }
        composable(Routes.SignUpScreen) { SignUpScreen(navController) }
        composable(Routes.MovieDetailScreen, arguments = listOf(
            navArgument("movie_id"){
                type = NavType.StringType
                nullable = true
            }
        )) { it ->
            val movieId = it.arguments?.getString("movie_id") ?: "tt0816692"
            val viewModel: MovieDetailViewModel = viewModel()

            movieId.let {
                viewModel.getMovieById(it)
            }
            MovieDetail(navController, id = movieId)
        }
        composable(Routes.RootBottomNavigationBar) { RootBottomNavBarScreen(navController) }
    }
}


