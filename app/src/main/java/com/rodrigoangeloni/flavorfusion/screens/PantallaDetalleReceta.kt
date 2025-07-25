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
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rodrigoangeloni.flavorfusion.R
import com.rodrigoangeloni.flavorfusion.model.Receta
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

    // Usar LaunchedEffect para cargar el detalle al entrar en la pantalla
    LaunchedEffect(key1 = recipeId) {
        if (recipeType == "meal") {
            viewModel.cargarDetalleComida(recipeId)
        } else {
            viewModel.cargarDetalleBebida(recipeId)
        }
    }

    val receta = if (recipeType == "meal") {
        uiState.detalleComida
    } else {
        uiState.detalleBebida
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = when (receta) {
                            is Receta.Comida -> receta.nombre
                            is Receta.Bebida -> receta.nombre
                            null -> stringResource(R.string.titulo_detalle_receta)
                        }
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
                    IconButton(onClick = {
                        receta?.let { currentReceta ->
                            when (currentReceta) {
                                is Receta.Comida -> viewModel.marcarComidaComoFavorita(currentReceta)
                                is Receta.Bebida -> viewModel.marcarBebidaComoFavorita(currentReceta)
                            }
                        }
                    }) {
                        val isFavorite = when (receta) {
                            is Receta.Comida -> receta.esFavorito
                            is Receta.Bebida -> receta.esFavorito
                            null -> false
                        }
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = if (isFavorite) {
                                stringResource(R.string.quitar_favorito)
                            } else {
                                stringResource(R.string.marcar_favorito)
                            }
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        if (uiState.estaCargando) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (uiState.error != null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(uiState.error!!)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        if (recipeType == "meal") {
                            viewModel.cargarDetalleComida(recipeId)
                        } else {
                            viewModel.cargarDetalleBebida(recipeId)
                        }
                    }) {
                        Text(stringResource(R.string.reintentar))
                    }
                }
            }
        } else if (receta != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(scrollState)
            ) {
                // Imagen de la receta
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(
                            when (receta) {
                                is Receta.Comida -> receta.urlImagen
                                is Receta.Bebida -> receta.urlImagen
                            }
                        )
                        .crossfade(true)
                        .build(),
                    contentDescription = when (receta) {
                        is Receta.Comida -> receta.nombre
                        is Receta.Bebida -> receta.nombre
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp),
                    contentScale = ContentScale.Crop
                )

                // Información de la receta
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = when (receta) {
                            is Receta.Comida -> receta.nombre
                            is Receta.Bebida -> receta.nombre
                        },
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Category,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = stringResource(R.string.categoria) + ": " + when (receta) {
                                is Receta.Comida -> receta.categoria
                                is Receta.Bebida -> receta.categoria
                            },
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Información específica según el tipo de receta
                    when (receta) {
                        is Receta.Comida -> {
                            receta.pais?.let { pais ->
                                Spacer(modifier = Modifier.height(4.dp))
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Public,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = stringResource(R.string.pais_origen) + ": " + pais,
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                        }
                        is Receta.Bebida -> {
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.LocalBar,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = stringResource(R.string.tipo_vaso) + ": " + receta.tipoVaso,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            Spacer(modifier = Modifier.height(4.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = if (receta.esAlcoholica) Icons.Default.LocalBar else Icons.Default.Coffee,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = if (receta.esAlcoholica) {
                                        stringResource(R.string.alcoholica)
                                    } else {
                                        stringResource(R.string.no_alcoholica)
                                    },
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Ingredientes
                    Text(
                        text = stringResource(R.string.ingredientes),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    val ingredientes = when (receta) {
                        is Receta.Comida -> receta.ingredientes
                        is Receta.Bebida -> receta.ingredientes
                    }

                    ingredientes.forEach { ingrediente ->
                        Row(
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.FiberManualRecord,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier
                                    .size(10.dp)
                                    .padding(top = 6.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "${ingrediente.nombre} ${ingrediente.cantidad ?: ""}".trim(),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Instrucciones
                    Text(
                        text = stringResource(R.string.instrucciones),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = when (receta) {
                            is Receta.Comida -> receta.instrucciones
                            is Receta.Bebida -> receta.instrucciones
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = MaterialTheme.typography.bodyMedium.lineHeight
                    )

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        } else {
            // Estado cuando no hay receta cargada
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No se encontró información de la receta",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
