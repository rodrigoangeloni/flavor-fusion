package com.rodrigoangeloni.flavorfusion.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rodrigoangeloni.flavorfusion.components.TarjetaReceta
import com.rodrigoangeloni.flavorfusion.viewmodels.InicioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaFavoritos(
    viewModel: InicioViewModel = hiltViewModel(),
    onNavigateUp: () -> Unit,
    onMealClick: (String) -> Unit,
    onCocktailClick: (String) -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Comidas", "Bebidas")

    // Datos de ejemplo para favoritos
    val favoriteMeals = listOf(
        Triple("1", "Paella Valenciana", "Plato Principal"),
        Triple("2", "Pasta Carbonara", "Pasta"),
        Triple("3", "Sushi Roll", "Japonesa")
    )

    val favoriteCocktails = listOf(
        Triple("1", "Mojito Clásico", "Cóctel"),
        Triple("2", "Margarita", "Cóctel"),
        Triple("3", "Piña Colada", "Cóctel Tropical")
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis Favoritos") },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Tabs para alternar entre comidas y bebidas
            TabRow(selectedTabIndex = selectedTab) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) },
                        icon = {
                            Icon(
                                imageVector = if (index == 0) Icons.Default.Home else Icons.Default.Star,
                                contentDescription = null
                            )
                        }
                    )
                }
            }

            // Contenido según la tab seleccionada
            when (selectedTab) {
                0 -> {
                    // Favoritos de comidas
                    if (favoriteMeals.isEmpty()) {
                        EmptyFavoritesView(
                            message = "No tienes comidas favoritas",
                            icon = Icons.Default.Home
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(vertical = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(favoriteMeals) { (id, name, category) ->
                                TarjetaReceta(
                                    titulo = "Comida favorita",
                                    nombre = name,
                                    categoria = category,
                                    urlImagen = "https://via.placeholder.com/300x200",
                                    onClick = { onMealClick(id) }
                                )
                            }
                        }
                    }
                }
                1 -> {
                    // Favoritos de bebidas
                    if (favoriteCocktails.isEmpty()) {
                        EmptyFavoritesView(
                            message = "No tienes bebidas favoritas",
                            icon = Icons.Default.Star
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(vertical = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(favoriteCocktails) { (id, name, category) ->
                                TarjetaReceta(
                                    titulo = "Bebida favorita",
                                    nombre = name,
                                    categoria = category,
                                    urlImagen = "https://via.placeholder.com/300x200",
                                    onClick = { onCocktailClick(id) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptyFavoritesView(
    message: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = message,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Explora recetas y marca tus favoritas para verlas aquí",
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
            fontSize = 14.sp
        )
    }
}
