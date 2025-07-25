package com.rodrigoangeloni.flavorfusion.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodrigoangeloni.flavorfusion.model.Receta
import com.rodrigoangeloni.flavorfusion.repository.RecetasRepositorio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

// Estado UI para la pantalla de inicio
data class InicioEstadoUI(
    val comidaSugerida: Receta.Comida? = null,
    val bebidaSugerida: Receta.Bebida? = null,
    val resultadosBusquedaComidas: List<Receta.Comida> = emptyList(),
    val resultadosBusquedaBebidas: List<Receta.Bebida> = emptyList(),
    val detalleComida: Receta.Comida? = null,
    val detalleBebida: Receta.Bebida? = null,
    val estaCargando: Boolean = false,
    val estaBuscando: Boolean = false,
    val error: String? = null
)

@HiltViewModel
class InicioViewModel @Inject constructor(
    private val repositorio: RecetasRepositorio
) : ViewModel() {

    // Estado de la UI
    private val _estadoUI = MutableStateFlow(InicioEstadoUI(estaCargando = true))
    val estadoUI: StateFlow<InicioEstadoUI> = _estadoUI.asStateFlow()

    init {
        cargarSugerenciasAleatorias()
    }

    fun cargarSugerenciasAleatorias() {
        viewModelScope.launch {
            _estadoUI.update { it.copy(estaCargando = true) }

            try {
                // Cargar comida aleatoria
                repositorio.obtenerComidaAleatoria()
                    .collect { comida ->
                        _estadoUI.update { it.copy(comidaSugerida = comida) }
                    }

                // Cargar bebida aleatoria
                repositorio.obtenerBebidaAleatoria()
                    .collect { bebida ->
                        _estadoUI.update {
                            it.copy(
                                bebidaSugerida = bebida,
                                estaCargando = false
                            )
                        }
                    }
            } catch (e: Exception) {
                _estadoUI.update {
                    it.copy(
                        error = "Error al cargar sugerencias: ${e.message}",
                        estaCargando = false
                    )
                }
            }
        }
    }

    fun marcarComidaComoFavorita(comida: Receta.Comida) {
        viewModelScope.launch {
            try {
                repositorio.toggleComidaFavorita(comida)
                _estadoUI.update {
                    it.copy(
                        comidaSugerida = if (it.comidaSugerida?.id == comida.id) {
                            comida.copy(esFavorito = !comida.esFavorito)
                        } else it.comidaSugerida,
                        detalleComida = if (it.detalleComida?.id == comida.id) {
                            comida.copy(esFavorito = !comida.esFavorito)
                        } else it.detalleComida
                    )
                }
            } catch (e: Exception) {
                _estadoUI.update {
                    it.copy(error = "Error al actualizar favoritos: ${e.message}")
                }
            }
        }
    }

    fun marcarBebidaComoFavorita(bebida: Receta.Bebida) {
        viewModelScope.launch {
            try {
                repositorio.toggleBebidaFavorita(bebida)
                _estadoUI.update {
                    it.copy(
                        bebidaSugerida = if (it.bebidaSugerida?.id == bebida.id) {
                            bebida.copy(esFavorito = !bebida.esFavorito)
                        } else it.bebidaSugerida,
                        detalleBebida = if (it.detalleBebida?.id == bebida.id) {
                            bebida.copy(esFavorito = !bebida.esFavorito)
                        } else it.detalleBebida
                    )
                }
            } catch (e: Exception) {
                _estadoUI.update {
                    it.copy(error = "Error al actualizar favoritos: ${e.message}")
                }
            }
        }
    }

    fun buscarComidas(consulta: String) {
        viewModelScope.launch {
            _estadoUI.update { it.copy(estaBuscando = true) }

            try {
                repositorio.buscarComidas(consulta)
                    .collect { resultados ->
                        _estadoUI.update {
                            it.copy(
                                resultadosBusquedaComidas = resultados,
                                estaBuscando = false
                            )
                        }
                    }
            } catch (e: Exception) {
                _estadoUI.update {
                    it.copy(
                        error = "Error al buscar comidas: ${e.message}",
                        estaBuscando = false
                    )
                }
            }
        }
    }

    fun buscarBebidas(consulta: String) {
        viewModelScope.launch {
            _estadoUI.update { it.copy(estaBuscando = true) }

            try {
                repositorio.buscarBebidas(consulta)
                    .collect { resultados ->
                        _estadoUI.update {
                            it.copy(
                                resultadosBusquedaBebidas = resultados,
                                estaBuscando = false
                            )
                        }
                    }
            } catch (e: Exception) {
                _estadoUI.update {
                    it.copy(
                        error = "Error al buscar bebidas: ${e.message}",
                        estaBuscando = false
                    )
                }
            }
        }
    }

    fun limpiarResultadosBusqueda() {
        _estadoUI.update {
            it.copy(
                resultadosBusquedaComidas = emptyList(),
                resultadosBusquedaBebidas = emptyList()
            )
        }
    }

    fun limpiarError() {
        _estadoUI.update { it.copy(error = null) }
    }

    /**
     * Carga los detalles de una comida específica por su ID
     */
    fun cargarDetalleComida(id: String) {
        viewModelScope.launch {
            _estadoUI.update { it.copy(estaCargando = true, error = null) }

            try {
                repositorio.obtenerDetalleComida(id)
                    .collect { comida ->
                        _estadoUI.update {
                            it.copy(
                                detalleComida = comida,
                                estaCargando = false
                            )
                        }
                    }
            } catch (e: Exception) {
                _estadoUI.update {
                    it.copy(
                        error = "Error al cargar detalles de la comida: ${e.message}",
                        estaCargando = false
                    )
                }
            }
        }
    }

    /**
     * Carga los detalles de una bebida específica por su ID
     */
    fun cargarDetalleBebida(id: String) {
        viewModelScope.launch {
            _estadoUI.update { it.copy(estaCargando = true, error = null) }

            try {
                repositorio.obtenerDetalleBebida(id)
                    .collect { bebida ->
                        _estadoUI.update {
                            it.copy(
                                detalleBebida = bebida,
                                estaCargando = false
                            )
                        }
                    }
            } catch (e: Exception) {
                _estadoUI.update {
                    it.copy(
                        error = "Error al cargar detalles de la bebida: ${e.message}",
                        estaCargando = false
                    )
                }
            }
        }
    }
}