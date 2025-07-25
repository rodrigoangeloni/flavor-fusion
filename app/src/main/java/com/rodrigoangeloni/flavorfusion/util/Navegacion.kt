package com.rodrigoangeloni.flavorfusion.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.rodrigoangeloni.flavorfusion.screens.*

@Composable
fun NavegacionApp(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "inicio"
    ) {
        // Pantalla de inicio
        composable("inicio") {
            PantallaInicio(
                navegarAComidas = { navController.navigate("explorar_comidas") },
                navegarABebidas = { navController.navigate("explorar_bebidas") },
                navegarAFavoritos = { navController.navigate("favoritos") },
                navegarADetalleReceta = { id, tipo -> navController.navigate("detalle_receta/$id/$tipo") }
            )
        }

        // Explorar comidas
        composable("explorar_comidas") {
            PantallaExplorarComidas(
                onNavigateUp = { navController.navigateUp() },
                onNavigateToDetail = { id, tipo -> navController.navigate("detalle_receta/$id/$tipo") }
            )
        }

        // Explorar bebidas
        composable("explorar_bebidas") {
            PantallaExplorarBebidas(
                onNavigateUp = { navController.navigateUp() },
                onNavigateToDetail = { id, tipo -> navController.navigate("detalle_receta/$id/$tipo") }
            )
        }

        // Pantalla de favoritos
        composable("favoritos") {
            PantallaFavoritos(
                onRecetaClick = { id, tipo -> navController.navigate("detalle_receta/$id/$tipo") }
            )
        }

        // Detalle de receta
        composable(
            route = "detalle_receta/{recipeId}/{recipeType}",
            arguments = listOf(
                navArgument("recipeId") { type = NavType.StringType },
                navArgument("recipeType") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val recipeId = backStackEntry.arguments?.getString("recipeId") ?: ""
            val recipeType = backStackEntry.arguments?.getString("recipeType") ?: ""

            PantallaDetalleReceta(
                recipeId = recipeId,
                recipeType = recipeType,
                onNavigateUp = { navController.navigateUp() }
            )
        }
    }
}