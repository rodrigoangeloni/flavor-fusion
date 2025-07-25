package com.rodrigoangeloni.flavorfusion.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.rodrigoangeloni.flavorfusion.screens.*

@Composable
fun AppNavegacion() {
    val navController = rememberNavController()

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
                navegarADetalleComida = { id -> navController.navigate("detalle_receta/$id/meal") },
                navegarADetalleBebida = { id -> navController.navigate("detalle_receta/$id/cocktail") }
            )
        }

        // Explorar comidas
        composable("explorar_comidas") {
            PantallaExplorarComidas(
                onNavigateUp = { navController.navigateUp() },
                onNavigateToDetail = { id -> navController.navigate("detalle_receta/$id/meal") }
            )
        }

        // Explorar bebidas
        composable("explorar_bebidas") {
            PantallaExplorarBebidas(
                onNavigateUp = { navController.navigateUp() },
                onNavigateToDetail = { id -> navController.navigate("detalle_receta/$id/cocktail") }
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
            val recipeType = backStackEntry.arguments?.getString("recipeType") ?: "meal"

            PantallaDetalleReceta(
                recipeId = recipeId,
                recipeType = recipeType,
                onNavigateUp = { navController.navigateUp() }
            )
        }

        // Favoritos
        composable("favoritos") {
            PantallaFavoritos(
                onNavigateUp = { navController.navigateUp() },
                onMealClick = { id -> navController.navigate("detalle_receta/$id/meal") },
                onCocktailClick = { id -> navController.navigate("detalle_receta/$id/cocktail") }
            )
        }
    }
}