package com.rodrigoangeloni.flavorfusion.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.rodrigoangeloni.flavorfusion.R
import com.rodrigoangeloni.flavorfusion.util.TraductorBusqueda
import coil.request.ImageRequest
import coil.compose.AsyncImage

@Composable
fun TarjetaReceta(
    titulo: String,
    nombre: String,
    categoria: String,
    urlImagen: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable(onClick = onClick)
    ) {
        Column {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(urlImagen)
                        .crossfade(true)
                        .build(),
                    contentDescription = nombre,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentScale = ContentScale.Crop,
                    onError = {
                        // Callback cuando hay error - no mostramos nada aquí
                    },
                    onLoading = {
                        // Callback cuando está cargando - no mostramos nada aquí
                    }
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black.copy(alpha = 0.6f))
                        .padding(8.dp)
                        .align(Alignment.TopStart)
                ) {
                    Text(
                        text = titulo,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = nombre,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = categoria,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun MensajeError(
    mensaje: String,
    onReintentar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.error,
            modifier = Modifier.size(48.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = mensaje,
            color = MaterialTheme.colorScheme.error
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onReintentar) {
            Text("Reintentar")
        }
    }
}

@Composable
fun BarraBusqueda(
    consulta: String,
    onConsultaCambiada: (String) -> Unit,
    placeholder: String,
    onBuscar: () -> Unit = {}
) {
    OutlinedTextField(
        value = consulta,
        onValueChange = onConsultaCambiada,
        placeholder = { Text(placeholder) },
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription = "Buscar")
        },
        trailingIcon = {
            Row {
                if (consulta.isNotEmpty()) {
                    IconButton(onClick = { onConsultaCambiada("") }) {
                        Icon(Icons.Default.Clear, contentDescription = "Limpiar")
                    }
                    IconButton(onClick = onBuscar) {
                        Icon(Icons.Default.Search, contentDescription = "Buscar")
                    }
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { onBuscar() }
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Composable
fun BarraBusquedaAvanzada(
    consulta: String,
    onConsultaCambiada: (String) -> Unit,
    placeholder: String,
    onBuscar: () -> Unit = {},
    esComida: Boolean = true
) {
    val context = LocalContext.current
    var mostrarSugerencias by remember { mutableStateOf(false) }
    val sugerencias = remember { TraductorBusqueda.obtenerSugerencias(esComida) }

    Column {
        OutlinedTextField(
            value = consulta,
            onValueChange = {
                onConsultaCambiada(it)
                mostrarSugerencias = it.isEmpty()
            },
            placeholder = { Text(placeholder) },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = stringResource(R.string.buscar_accion))
            },
            trailingIcon = {
                Row {
                    if (consulta.isNotEmpty()) {
                        IconButton(onClick = {
                            onConsultaCambiada("")
                            mostrarSugerencias = true
                        }) {
                            Icon(Icons.Default.Clear, contentDescription = stringResource(R.string.limpiar_accion))
                        }
                        IconButton(onClick = onBuscar) {
                            Icon(Icons.Default.Search, contentDescription = stringResource(R.string.buscar_accion))
                        }
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onBuscar() }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .onFocusChanged { focusState ->
                    mostrarSugerencias = focusState.isFocused && consulta.isEmpty()
                }
        )

        // Mostrar información de traducción si es necesario
        if (consulta.isNotEmpty() && TraductorBusqueda.necesitaTraduccion(consulta, esComida)) {
            val consultaTraducida = TraductorBusqueda.traducirConsulta(consulta, esComida)
            val mensajeTraduccion = TraductorBusqueda.obtenerMensajeTraduccion(consulta, consultaTraducida)

            mensajeTraduccion?.let { mensaje ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = mensaje,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            }
        }

        // Mostrar sugerencias cuando el campo está vacío y enfocado
        if (mostrarSugerencias && consulta.isEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = if (esComida) stringResource(R.string.buscar_hint_comidas)
                              else stringResource(R.string.buscar_hint_bebidas),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(sugerencias) { sugerencia ->
                            SuggestionChip(
                                onClick = {
                                    onConsultaCambiada(sugerencia)
                                    mostrarSugerencias = false
                                    onBuscar()
                                },
                                label = { Text(sugerencia) }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = stringResource(R.string.busqueda_sugerencia),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
