package com.rodrigoangeloni.flavorfusion.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.rodrigoangeloni.flavorfusion.R
import com.rodrigoangeloni.flavorfusion.viewmodels.InicioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaInicio(
    viewModel: InicioViewModel = hiltViewModel(),
    navegarAComidas: () -> Unit,
    navegarABebidas: () -> Unit,
    navegarAFavoritos: () -> Unit,
    navegarADetalleReceta: (String, String) -> Unit
) {
    val estadoUI by viewModel.estadoUI.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    // Título comentado para mostrar solo el logo
                    Text(
                        text = "Menú Principal",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                },
                actions = {
                    IconButton(onClick = navegarAFavoritos) {
                        Icon(Icons.Default.Favorite, contentDescription = "Favoritos")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Logo personalizado
//            Image(
//                painter = painterResource(id = R.drawable.fusiondesabores), // Cambia "logo_personalizado" por el nombre de tu archivo PNG
//                contentDescription = "FlavorFusion Logo",
//                modifier = Modifier
//                    .size(120.dp)
//                    .align(Alignment.CenterHorizontally)
//                    .padding(bottom = 16.dp),
//                contentScale = ContentScale.Fit
//            )

            // Título de bienvenida
//            Text(
//                text = "¡Descubre Nuevos Sabores!",
//                style = MaterialTheme.typography.headlineMedium,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.padding(bottom = 24.dp)
//            )


            if (estadoUI.estaCargando) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else {
                // Comida sugerida
                estadoUI.comidaSugerida?.let { comida ->
                    TarjetaSugerencia(
                        titulo = "Comida del Día",
                        receta = comida,
                        esFavorito = estadoUI.favoritosIds.contains(comida.id),
                        onRecetaClick = { navegarADetalleReceta(comida.id, "meal") },
                        onFavoritoClick = { viewModel.alternarFavorito(comida) }
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Bebida sugerida
                estadoUI.bebidaSugerida?.let { bebida ->
                    TarjetaSugerencia(
                        titulo = "Bebida del Día",
                        receta = bebida,
                        esFavorito = estadoUI.favoritosIds.contains(bebida.id),
                        onRecetaClick = { navegarADetalleReceta(bebida.id, "drink") },
                        onFavoritoClick = { viewModel.alternarFavorito(bebida) }
                    )

                    Spacer(modifier = Modifier.height(24.dp))
                }

                // Botones de navegación
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Botón explorar comidas
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { navegarAComidas() },
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Default.Restaurant,
                                contentDescription = null,
                                modifier = Modifier.size(32.dp),
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Explorar Comidas",
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    }

                    // Botón explorar bebidas
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .clickable { navegarABebidas() },
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocalBar,
                                contentDescription = null,
                                modifier = Modifier.size(32.dp),
                                tint = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Explorar Bebidas",
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                    }
                }
            }

            // Mostrar error si existe
            estadoUI.error?.let { error ->
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Error",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onErrorContainer,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = error,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onErrorContainer
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = { viewModel.cargarSugerenciasAleatorias() }
                        ) {
                            Text("Reintentar")
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TarjetaSugerencia(
    titulo: String,
    receta: com.rodrigoangeloni.flavorfusion.model.Receta,
    esFavorito: Boolean,
    onRecetaClick: () -> Unit,
    onFavoritoClick: () -> Unit
) {
    Column {
        Text(
            text = titulo,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onRecetaClick() }
        ) {
            Column {
                Box {
                    AsyncImage(
                        model = receta.imagen,
                        contentDescription = receta.nombre,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentScale = ContentScale.Crop
                    )

                    // Botón de favorito flotante
                    IconButton(
                        onClick = onFavoritoClick,
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                            .background(
                                MaterialTheme.colorScheme.surface.copy(alpha = 0.8f),
                                RoundedCornerShape(50)
                            )
                    ) {
                        Icon(
                            imageVector = if (esFavorito) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                            contentDescription = if (esFavorito) "Quitar de favoritos" else "Agregar a favoritos",
                            tint = if (esFavorito) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = receta.nombre,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )

                    if (receta.categoria.isNotBlank()) {
                        Text(
                            text = receta.categoria,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    if (receta.area.isNotBlank()) {
                        Text(
                            text = receta.area,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(top = 2.dp)
                        )
                    }
                }
            }
        }
    }
}