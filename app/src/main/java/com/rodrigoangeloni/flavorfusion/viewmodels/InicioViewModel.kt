package com.rodrigoangeloni.flavorfusion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoangeloni.flavorfusion.model.*
import com.rodrigoangeloni.flavorfusion.repository.RecetasRepositorio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

// Estado UI para la aplicación
data class InicioEstadoUI(
    val comidaSugerida: Receta? = null,
    val bebidaSugerida: Receta? = null,
    val resultadosBusquedaComidas: List<Receta> = emptyList(),
    val resultadosBusquedaBebidas: List<Receta> = emptyList(),
    val detalleComida: Receta? = null,
    val detalleBebida: Receta? = null,
    val estaCargando: Boolean = false,
    val estaBuscando: Boolean = false,
    val error: String? = null,
    val favoritosIds: Set<String> = emptySet()
)

@HiltViewModel
class InicioViewModel @Inject constructor(
    private val repositorio: RecetasRepositorio
) : ViewModel() {

    // Estado de la UI
    private val _estadoUI = MutableStateFlow(InicioEstadoUI(estaCargando = true))
    val estadoUI: StateFlow<InicioEstadoUI> = _estadoUI.asStateFlow()

    // Flow de favoritos
    val favoritos: StateFlow<List<Receta>> = repositorio.obtenerFavoritos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        cargarSugerenciasAleatorias()
        observarFavoritos()
    }

    private fun observarFavoritos() {
        viewModelScope.launch {
            favoritos.collect { listaFavoritos ->
                val favoritosIds = listaFavoritos.map { it.id }.toSet()
                _estadoUI.update { it.copy(favoritosIds = favoritosIds) }
            }
        }
    }

    fun cargarSugerenciasAleatorias() {
        viewModelScope.launch {
            _estadoUI.update { it.copy(estaCargando = true, error = null) }

            try {
                // Cargar comida aleatoria
                val comidaResponse = repositorio.obtenerComidaAleatoria()
                val comidaSugerida = comidaResponse?.meals?.firstOrNull()?.toReceta()

                // Cargar bebida aleatoria
                val bebidaResponse = repositorio.obtenerBebidaAleatoria()
                val bebidaSugerida = bebidaResponse?.drinks?.firstOrNull()?.toReceta()

                _estadoUI.update {
                    it.copy(
                        comidaSugerida = comidaSugerida,
                        bebidaSugerida = bebidaSugerida,
                        estaCargando = false
                    )
                }
            } catch (e: Exception) {
                _estadoUI.update {
                    it.copy(
                        estaCargando = false,
                        error = "Error al cargar sugerencias: ${e.message}"
                    )
                }
            }
        }
    }

    fun buscarComidas(query: String) {
        viewModelScope.launch {
            _estadoUI.update { it.copy(estaBuscando = true, error = null) }

            try {
                val response = repositorio.buscarComidas(query)
                val recetas = response?.meals?.map { it.toReceta() } ?: emptyList()

                _estadoUI.update {
                    it.copy(
                        resultadosBusquedaComidas = recetas,
                        estaBuscando = false
                    )
                }
            } catch (e: Exception) {
                _estadoUI.update {
                    it.copy(
                        estaBuscando = false,
                        error = "Error en búsqueda: ${e.message}"
                    )
                }
            }
        }
    }

    fun buscarBebidas(query: String) {
        viewModelScope.launch {
            _estadoUI.update { it.copy(estaBuscando = true, error = null) }

            try {
                val response = repositorio.buscarBebidas(query)
                val recetas = response?.drinks?.map { it.toReceta() } ?: emptyList()

                _estadoUI.update {
                    it.copy(
                        resultadosBusquedaBebidas = recetas,
                        estaBuscando = false
                    )
                }
            } catch (e: Exception) {
                _estadoUI.update {
                    it.copy(
                        estaBuscando = false,
                        error = "Error en búsqueda: ${e.message}"
                    )
                }
            }
        }
    }

    fun cargarDetalleComida(id: String) {
        viewModelScope.launch {
            _estadoUI.update { it.copy(estaCargando = true, error = null) }

            try {
                val response = repositorio.obtenerDetalleComida(id)
                val receta = response?.meals?.firstOrNull()?.toReceta()

                _estadoUI.update {
                    it.copy(
                        detalleComida = receta,
                        estaCargando = false
                    )
                }
            } catch (e: Exception) {
                _estadoUI.update {
                    it.copy(
                        estaCargando = false,
                        error = "Error al cargar detalle: ${e.message}"
                    )
                }
            }
        }
    }

    fun cargarDetalleBebida(id: String) {
        viewModelScope.launch {
            _estadoUI.update { it.copy(estaCargando = true, error = null) }

            try {
                val response = repositorio.obtenerDetalleBebida(id)
                val receta = response?.drinks?.firstOrNull()?.toReceta()

                _estadoUI.update {
                    it.copy(
                        detalleBebida = receta,
                        estaCargando = false
                    )
                }
            } catch (e: Exception) {
                _estadoUI.update {
                    it.copy(
                        estaCargando = false,
                        error = "Error al cargar detalle: ${e.message}"
                    )
                }
            }
        }
    }

    // Funciones de favoritos
    suspend fun esFavorito(id: String): Boolean {
        return repositorio.esFavorito(id)
    }

    fun alternarFavorito(receta: Receta) {
        viewModelScope.launch {
            try {
                repositorio.alternarFavorito(receta)
            } catch (e: Exception) {
                _estadoUI.update {
                    it.copy(error = "Error al guardar favorito: ${e.message}")
                }
            }
        }
    }

    fun agregarFavorito(receta: Receta) {
        viewModelScope.launch {
            try {
                repositorio.agregarFavorito(receta)
            } catch (e: Exception) {
                _estadoUI.update {
                    it.copy(error = "Error al agregar favorito: ${e.message}")
                }
            }
        }
    }

    fun eliminarFavorito(id: String) {
        viewModelScope.launch {
            try {
                repositorio.eliminarFavorito(id)
            } catch (e: Exception) {
                _estadoUI.update {
                    it.copy(error = "Error al eliminar favorito: ${e.message}")
                }
            }
        }
    }

    fun limpiarError() {
        _estadoUI.update { it.copy(error = null) }
    }

    fun limpiarResultadosBusqueda() {
        _estadoUI.update {
            it.copy(
                resultadosBusquedaComidas = emptyList(),
                resultadosBusquedaBebidas = emptyList()
            )
        }
    }
}