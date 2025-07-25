package com.rodrigoangeloni.flavorfusion.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.rodrigoangeloni.flavorfusion.components.MensajeError
import com.rodrigoangeloni.flavorfusion.components.TarjetaReceta
import com.rodrigoangeloni.flavorfusion.viewmodels.InicioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaInicio(
    viewModel: InicioViewModel = hiltViewModel(),
    navegarAComidas: () -> Unit,
    navegarABebidas: () -> Unit,
    navegarAFavoritos: () -> Unit,
    navegarADetalleComida: (String) -> Unit,
    navegarADetalleBebida: (String) -> Unit
) {
    val estadoUI by viewModel.estadoUI.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Flavor Fusion") },
                actions = {
                    IconButton(onClick = navegarAFavoritos) {
                        Icon(Icons.Default.Favorite, contentDescription = "Favoritos")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Banner de bienvenida
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "¿Qué te gustaría preparar hoy?",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = navegarAComidas) {
                            Icon(Icons.Default.Home, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Comidas")
                        }

                        Button(
                            onClick = navegarABebidas,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            )
                        ) {
                            Icon(Icons.Default.Star, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Bebidas")
                        }
                    }
                }
            }

            // Descripción de la app
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "¿Qué es Flavor Fusion?",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        "Flavor Fusion es tu asistente culinario que te ayuda a descubrir " +
                                "deliciosas recetas de comidas y bebidas de todo el mundo."
                    )
                }
            }

            // Sección de sugerencias
            Text(
                text = "Sugerencias para hoy",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            if (estadoUI.estaCargando) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (estadoUI.error != null) {
                MensajeError(
                    mensaje = estadoUI.error!!,
                    onReintentar = viewModel::cargarSugerenciasAleatorias
                )
            } else {
                // Sugerencia de comida
                estadoUI.comidaSugerida?.let { comida ->
                    TarjetaReceta(
                        titulo = "Comida recomendada",
                        nombre = comida.nombre,
                        categoria = comida.categoria,
                        urlImagen = comida.urlImagen,
                        onClick = { navegarADetalleComida(comida.id) }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Sugerencia de bebida
                estadoUI.bebidaSugerida?.let { bebida ->
                    TarjetaReceta(
                        titulo = "Bebida recomendada",
                        nombre = bebida.nombre,
                        categoria = bebida.categoria,
                        urlImagen = bebida.urlImagen,
                        onClick = { navegarADetalleBebida(bebida.id) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}