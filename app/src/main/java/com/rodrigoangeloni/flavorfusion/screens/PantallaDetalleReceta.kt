package com.rodrigoangeloni.flavorfusion.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rodrigoangeloni.flavorfusion.R
import com.rodrigoangeloni.flavorfusion.model.Receta
import com.rodrigoangeloni.flavorfusion.model.getIngredientesList
import com.rodrigoangeloni.flavorfusion.viewmodels.InicioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaDetalleReceta(
    recipeId: String,
    recipeType: String,
    onNavigateUp: () -> Unit,
    viewModel: InicioViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val uiState by viewModel.estadoUI.collectAsState()

    // Obtener estado de favorito usando LaunchedEffect
    var esFavorito by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = recipeId) {
        if (recipeType == "meal") {
            viewModel.cargarDetalleComida(recipeId)
        } else {
            viewModel.cargarDetalleBebida(recipeId)
        }
        // Verificar si es favorito
        esFavorito = viewModel.esFavorito(recipeId)
    }

    val receta = if (recipeType == "meal") {
        uiState.detalleComida
    } else {
        uiState.detalleBebida
    }

    // Observar cambios en favoritos
    LaunchedEffect(uiState.favoritosIds) {
        esFavorito = uiState.favoritosIds.contains(recipeId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = receta?.nombre ?: stringResource(R.string.titulo_detalle_receta)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.regresar)
                        )
                    }
                },
                actions = {
                    receta?.let { currentReceta ->
                        IconButton(
                            onClick = {
                                viewModel.alternarFavorito(currentReceta)
                            }
                        ) {
                            Icon(
                                imageVector = if (esFavorito) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                                contentDescription = if (esFavorito) "Quitar de favoritos" else "Agregar a favoritos",
                                tint = if (esFavorito) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        if (uiState.estaCargando) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (receta != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(scrollState)
            ) {
                // Imagen principal
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(receta.imagen)
                        .crossfade(true)
                        .build(),
                    contentDescription = receta.nombre,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    // Título
                    Text(
                        text = receta.nombre,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Información adicional
                    if (receta.categoria.isNotBlank()) {
                        Text(
                            text = "Categoría: ${receta.categoria}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                    }

                    if (receta.area.isNotBlank()) {
                        Text(
                            text = if (receta.tipo == "meal") "Origen: ${receta.area}" else "Tipo: ${receta.area}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }

                    // Ingredientes
                    if (receta.ingredientes.isNotBlank()) {
                        Text(
                            text = "Ingredientes:",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        val ingredientesList = receta.getIngredientesList()
                        ingredientesList.forEach { ingrediente ->
                            if (ingrediente.isNotBlank()) {
                                Text(
                                    text = "• $ingrediente",
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = Modifier.padding(bottom = 4.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))
                    }

                    // Instrucciones
                    if (receta.instrucciones.isNotBlank()) {
                        Text(
                            text = "Instrucciones:",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Text(
                            text = receta.instrucciones,
                            style = MaterialTheme.typography.bodyMedium,
                            lineHeight = 20.sp
                        )
                    }
                }
            }
        } else {
            // Estado de error
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.Error,
                        contentDescription = null,
                        modifier = Modifier.size(64.dp),
                        tint = MaterialTheme.colorScheme.error
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Error al cargar la receta",
                        style = MaterialTheme.typography.headlineSmall,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = {
                            if (recipeType == "meal") {
                                viewModel.cargarDetalleComida(recipeId)
                            } else {
                                viewModel.cargarDetalleBebida(recipeId)
                            }
                        }
                    ) {
                        Text("Reintentar")
                    }
                }
            }
        }
    }
}
