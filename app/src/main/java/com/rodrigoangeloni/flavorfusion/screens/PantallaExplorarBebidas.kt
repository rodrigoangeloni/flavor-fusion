package com.rodrigoangeloni.flavorfusion.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rodrigoangeloni.flavorfusion.components.BarraBusqueda
import com.rodrigoangeloni.flavorfusion.components.BarraBusquedaAvanzada
import com.rodrigoangeloni.flavorfusion.components.MensajeError
import com.rodrigoangeloni.flavorfusion.components.TarjetaReceta
import com.rodrigoangeloni.flavorfusion.viewmodels.InicioViewModel
import androidx.compose.ui.Alignment
import com.rodrigoangeloni.flavorfusion.R
import androidx.compose.ui.res.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaExplorarBebidas(
    viewModel: InicioViewModel = hiltViewModel(),
    onNavigateToDetail: (String) -> Unit,
    onNavigateUp: () -> Unit
) {
    val uiState by viewModel.estadoUI.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.titulo_explorar_bebidas)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(Icons.Default.ArrowBack, contentDescription = stringResource(R.string.regresar))
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
            // Barra de búsqueda avanzada con traducción para bebidas
            BarraBusquedaAvanzada(
                consulta = searchQuery,
                onConsultaCambiada = {
                    searchQuery = it
                    if (it.isEmpty()) {
                        viewModel.limpiarResultadosBusqueda()
                    }
                },
                placeholder = stringResource(R.string.buscar_bebidas_placeholder),
                onBuscar = {
                    if (searchQuery.isNotBlank()) {
                        viewModel.buscarBebidas(searchQuery)
                    }
                },
                esComida = false // Importante: false para bebidas
            )

            when {
                uiState.estaBuscando -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = stringResource(R.string.cargando),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
                uiState.resultadosBusquedaBebidas.isNotEmpty() -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(uiState.resultadosBusquedaBebidas) { bebida ->
                            TarjetaReceta(
                                titulo = stringResource(R.string.resultado_busqueda),
                                nombre = bebida.nombre,
                                categoria = bebida.categoria,
                                urlImagen = bebida.urlImagen,
                                onClick = { onNavigateToDetail(bebida.id) }
                            )
                        }
                    }
                }
                searchQuery.isNotBlank() && uiState.resultadosBusquedaBebidas.isEmpty() && !uiState.estaBuscando -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                modifier = Modifier.size(64.dp),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = stringResource(R.string.sin_resultados, searchQuery),
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                uiState.estaCargando -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = stringResource(R.string.cargando),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
                uiState.error != null -> {
                    MensajeError(
                        mensaje = uiState.error!!,
                        onReintentar = viewModel::cargarSugerenciasAleatorias
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Mostrar bebida sugerida si existe
                        uiState.bebidaSugerida?.let { bebida ->
                            item {
                                TarjetaReceta(
                                    titulo = stringResource(R.string.bebida_destacada),
                                    nombre = bebida.nombre,
                                    categoria = bebida.categoria,
                                    urlImagen = bebida.urlImagen,
                                    onClick = { onNavigateToDetail(bebida.id) }
                                )
                            }
                        }

                        // Mostrar mensaje si no hay bebida sugerida
                        if (uiState.bebidaSugerida == null) {
                            item {
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp)
                                ) {
                                    Column(
                                        modifier = Modifier.padding(16.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Fastfood,
                                            contentDescription = null,
                                            modifier = Modifier.size(48.dp),
                                            tint = MaterialTheme.colorScheme.primary
                                        )
                                        Spacer(modifier = Modifier.height(8.dp))
                                        Text(
                                            text = "¡Descubre bebidas y cócteles!",
                                            style = MaterialTheme.typography.headlineSmall,
                                            textAlign = TextAlign.Center
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "Escribe 'mojito', 'café', 'cerveza' o cualquier bebida en español",
                                            style = MaterialTheme.typography.bodyMedium,
                                            textAlign = TextAlign.Center,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
