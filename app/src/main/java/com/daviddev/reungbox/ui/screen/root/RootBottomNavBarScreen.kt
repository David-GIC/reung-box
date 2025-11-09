package com.daviddev.reungbox.ui.screen.root

import android.annotation.SuppressLint
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.daviddev.reungbox.R
import com.daviddev.reungbox.domain.models.Movie
import com.daviddev.reungbox.ui.screen.account.AccountScreen
import com.daviddev.reungbox.ui.screen.download.DownloadScreen
import com.daviddev.reungbox.ui.screen.home.HomeScreen
import com.daviddev.reungbox.ui.screen.home.HomeViewModel
import com.daviddev.reungbox.ui.screen.movie_detail.MovieDetail
import com.daviddev.reungbox.ui.screen.root.components.CustomBottomBar
import com.daviddev.reungbox.ui.screen.saved.SavedScreen
import com.daviddev.reungbox.ui.screen.search.SearchScreen
import com.daviddev.reungbox.ui.theme.BackgroundColor
import com.daviddev.reungbox.ui.theme.WhiteColor
//import com.daviddev.reungbox.utils.NavigationUtils
import com.daviddev.reungbox.utils.Routes
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RootBottomNavBarScreen(screenNavController: NavHostController){
    val rootNavBarController = rememberNavController()
    val bottomNavItems = listOf(Screen.Home, Screen.Search, Screen.Saved, Screen.Download, Screen.Account)

    Scaffold(
        bottomBar = { CustomBottomBar(rootNavBarController, bottomNavItems) }
    ) {
        NavHost(
            navController = rootNavBarController,
            startDestination = Routes.HomeScreen,
            modifier = Modifier.padding(0.dp)
        ) {
            composable(Routes.HomeScreen) { HomeScreen(rootNavBarController, screenNavController) }
            composable(Routes.SearchScreen) { SearchScreen(rootNavBarController, screenNavController) }
            composable(Routes.SavedScreen) { SavedScreen(rootNavBarController, screenNavController) }
            composable(Routes.DownloadScreen) { DownloadScreen(rootNavBarController, screenNavController) }
            composable(Routes.AccountScreen) { AccountScreen(rootNavBarController, screenNavController) }
        }
    }
}

// Sealed class for screens
sealed class Screen(val route: String, val title: String, val icon: @Composable () -> Unit) {
    object Home   : Screen(Routes.HomeScreen,   "Home",   { Icon(painterResource(R.drawable.ic_home), contentDescription = "Home",
        Modifier.height(30.dp).width(30.dp)
    ) })
    object Search : Screen(Routes.SearchScreen, "Search", { Icon(painterResource(R.drawable.ic_search), contentDescription = "Search",
        Modifier.height(30.dp).width(30.dp)) })
    object Saved  : Screen(Routes.SavedScreen,  "Saved",  { Icon(painterResource(R.drawable.ic_saved), contentDescription = "Saved",
        Modifier.height(30.dp).width(30.dp)) })
    object Download  : Screen(Routes.DownloadScreen,  "Download",  { Icon(painterResource(R.drawable.ic_download), contentDescription = "Download",
        Modifier.height(30.dp).width(30.dp)) })
    object Account  : Screen(Routes.AccountScreen,  "Account",  { Icon(painterResource(R.drawable.ic_account), contentDescription = "Account",
        Modifier.height(30.dp).width(30.dp)) })
}

//@Preview(showBackground = true)
//@Composable
//fun MyAppPreview2() {
//    RootBottomNavBarScreen()
//}