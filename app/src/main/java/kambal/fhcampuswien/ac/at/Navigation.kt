package kambal.fhcampuswien.ac.at

import androidx.compose.runtime.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun myNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Homescreen"){
        composable("Homescreen"){ HeaderMenu(navController) }
        composable(
            route = "Detailscreen/{movieId}",
            arguments = listOf(
                navArgument(name="movieId"){
                    type = NavType.StringType
                }
            )
        ){ backstackentry ->
            val movieId = backstackentry.arguments?.getString("movieId")
            DetailHeader(movieId = movieId)
        }
    }


}