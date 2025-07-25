package com.rodrigoangeloni.flavorfusion.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rodrigoangeloni.flavorfusion.R
import com.rodrigoangeloni.flavorfusion.components.BarraBusqueda
import com.rodrigoangeloni.flavorfusion.components.BarraBusquedaAvanzada
import com.rodrigoangeloni.flavorfusion.components.MensajeError
import com.rodrigoangeloni.flavorfusion.components.TarjetaReceta
import com.rodrigoangeloni.flavorfusion.viewmodels.InicioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaExplorarComidas(
    viewModel: InicioViewModel = hiltViewModel(),
    onNavigateToDetail: (String) -> Unit,
    onNavigateUp: () -> Unit
) {
    val uiState by viewModel.estadoUI.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.titulo_explorar_comidas)) },
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
            // Barra de búsqueda avanzada con traducción
            BarraBusquedaAvanzada(
                consulta = searchQuery,
                onConsultaCambiada = {
                    searchQuery = it
                    if (it.isEmpty()) {
                        viewModel.limpiarResultadosBusqueda()
                    }
                },
                placeholder = stringResource(R.string.buscar_comidas_placeholder),
                onBuscar = {
                    if (searchQuery.isNotBlank()) {
                        viewModel.buscarComidas(searchQuery)
                    }
                },
                esComida = true
            )

            when {
                uiState.estaBuscando -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
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
                uiState.resultadosBusquedaComidas.isNotEmpty() -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(uiState.resultadosBusquedaComidas) { comida ->
                            TarjetaReceta(
                                titulo = stringResource(R.string.resultado_busqueda),
                                nombre = comida.nombre,
                                categoria = comida.categoria,
                                urlImagen = comida.urlImagen,
                                onClick = { onNavigateToDetail(comida.id) }
                            )
                        }
                    }
                }
                searchQuery.isNotBlank() && uiState.resultadosBusquedaComidas.isEmpty() && !uiState.estaBuscando -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
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
                        contentAlignment = androidx.compose.ui.Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
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
                        // Mostrar comida sugerida si existe
                        uiState.comidaSugerida?.let { comida ->
                            item {
                                TarjetaReceta(
                                    titulo = stringResource(R.string.comida_destacada),
                                    nombre = comida.nombre,
                                    categoria = comida.categoria,
                                    urlImagen = comida.urlImagen,
                                    onClick = { onNavigateToDetail(comida.id) }
                                )
                            }
                        }

                        // Mostrar mensaje si no hay comida sugerida
                        if (uiState.comidaSugerida == null) {
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
                                            text = "¡Busca tus recetas favoritas!",
                                            style = MaterialTheme.typography.headlineSmall,
                                            textAlign = TextAlign.Center
                                        )
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = "Escribe 'pollo', 'pasta', 'tacos' o cualquier comida en español",
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
