package com.example.compose_material3_test

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_material3_test.ui.home.Home
import com.example.compose_material3_test.ui.search.Search

enum class Views(
    val route: String,
    val baseRoute: String = route,
    val arguments: List<NamedNavArgument> = emptyList(),
    val content: @Composable (NavController, NavBackStackEntry) -> Unit
) {
    HOME(
        route = "home",
        content = { controller, _ ->
            Home(
                onNavigateToSearch = { query ->
                    controller.navigate("${SEARCH.baseRoute}/${query}")
                }
            )
        }
    ),
    SEARCH(
        baseRoute = "search",
        route = "search/{query}",
        arguments = listOf(navArgument("query") { type = NavType.StringType }),
        content = { controller, backStackEntry ->
            Search(
//                query = backStackEntry.arguments?.getString("query") ?: "",
                onBack = {
                    controller.popBackStack()
                }
            )
        }
    )
}

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
){
    NavHost(navController = navController, startDestination = Views.HOME.route) {
        Views.values().forEach { view ->
            composable(
                route = view.route,
                arguments = view.arguments
            ) {
                view.content(navController, it)
            }
        }
    }
}