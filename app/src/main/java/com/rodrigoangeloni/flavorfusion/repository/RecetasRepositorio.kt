package com.rodrigoangeloni.flavorfusion.repository

import com.rodrigoangeloni.flavorfusion.database.FavoritosDao
import com.rodrigoangeloni.flavorfusion.database.ComidaFavoritaEntidad
import com.rodrigoangeloni.flavorfusion.database.BebidaFavoritaEntidad
import com.rodrigoangeloni.flavorfusion.model.*
import com.rodrigoangeloni.flavorfusion.network.*
import com.rodrigoangeloni.flavorfusion.util.TraductorBusqueda
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecetasRepositorio @Inject constructor(
    private val servicioComidas: ServicioAPIComidas,
    private val servicioBebidas: ServicioAPIBebidas,
    private val favoritosDao: FavoritosDao,
    private val gson: Gson
) {
    // Obtener comida aleatoria
    fun obtenerComidaAleatoria(): Flow<Receta.Comida?> = flow {
        try {
            val respuesta = servicioComidas.obtenerComidaAleatoria()
            val comidaDto = respuesta.meals?.firstOrNull()

            if (comidaDto != null) {
                val comida = Receta.Comida(
                    id = comidaDto.idMeal,
                    nombre = comidaDto.strMeal,
                    categoria = comidaDto.strCategory ?: "",
                    pais = comidaDto.strArea,
                    instrucciones = comidaDto.strInstructions ?: "",
                    urlImagen = comidaDto.strMealThumb ?: "",
                    ingredientes = obtenerIngredientesDeComidaDTO(comidaDto),
                    esFavorito = favoritosDao.esComidaFavorita(comidaDto.idMeal)
                )
                emit(comida)
            } else {
                emit(null)
            }
        } catch (e: Exception) {
            emit(null)
        }
    }

    // Obtener bebida aleatoria
    fun obtenerBebidaAleatoria(): Flow<Receta.Bebida?> = flow {
        try {
            val respuesta = servicioBebidas.obtenerBebidaAleatoria()
            val bebidaDto = respuesta.drinks?.firstOrNull()

            if (bebidaDto != null) {
                val bebida = Receta.Bebida(
                    id = bebidaDto.idDrink,
                    nombre = bebidaDto.strDrink,
                    categoria = bebidaDto.strCategory ?: "",
                    esAlcoholica = bebidaDto.strAlcoholic?.contains("Alcoholic") ?: false,
                    tipoVaso = bebidaDto.strGlass ?: "",
                    instrucciones = bebidaDto.strInstructions ?: "",
                    urlImagen = bebidaDto.strDrinkThumb ?: "",
                    ingredientes = obtenerIngredientesDeBebidaDTO(bebidaDto),
                    esFavorito = favoritosDao.esBebidaFavorita(bebidaDto.idDrink)
                )
                emit(bebida)
            } else {
                emit(null)
            }
        } catch (e: Exception) {
            emit(null)
        }
    }

    // Marcar/desmarcar comida como favorita
    suspend fun toggleComidaFavorita(comida: Receta.Comida) {
        if (comida.esFavorito) {
            favoritosDao.eliminarComidaFavorita(comida.id)
        } else {
            val entidad = ComidaFavoritaEntidad(
                id = comida.id,
                nombre = comida.nombre,
                categoria = comida.categoria,
                pais = comida.pais,
                instrucciones = comida.instrucciones,
                urlImagen = comida.urlImagen,
                ingredientes = gson.toJson(comida.ingredientes)
            )
            favoritosDao.insertarComidaFavorita(entidad)
        }
    }

    // Marcar/desmarcar bebida como favorita
    suspend fun toggleBebidaFavorita(bebida: Receta.Bebida) {
        if (bebida.esFavorito) {
            favoritosDao.eliminarBebidaFavorita(bebida.id)
        } else {
            val entidad = BebidaFavoritaEntidad(
                id = bebida.id,
                nombre = bebida.nombre,
                categoria = bebida.categoria,
                esAlcoholica = bebida.esAlcoholica,
                tipoVaso = bebida.tipoVaso,
                instrucciones = bebida.instrucciones,
                urlImagen = bebida.urlImagen,
                ingredientes = gson.toJson(bebida.ingredientes)
            )
            favoritosDao.insertarBebidaFavorita(entidad)
        }
    }

    // Funciones auxiliares
    private fun obtenerIngredientesDeComidaDTO(dto: ComidaDTO): List<Ingrediente> {
        val ingredientes = mutableListOf<Ingrediente>()

        // Versión simplificada para el ejemplo
        if (!dto.strIngredient1.isNullOrBlank()) {
            ingredientes.add(Ingrediente(dto.strIngredient1, dto.strMeasure1))
        }
        if (!dto.strIngredient2.isNullOrBlank()) {
            ingredientes.add(Ingrediente(dto.strIngredient2, dto.strMeasure2))
        }
        if (!dto.strIngredient3.isNullOrBlank()) {
            ingredientes.add(Ingrediente(dto.strIngredient3, dto.strMeasure3))
        }

        return ingredientes
    }

    private fun obtenerIngredientesDeBebidaDTO(dto: BebidaDTO): List<Ingrediente> {
        val ingredientes = mutableListOf<Ingrediente>()

        // Similar a la función para comidas
        if (!dto.strIngredient1.isNullOrBlank()) {
            ingredientes.add(Ingrediente(dto.strIngredient1, dto.strMeasure1))
        }
        if (!dto.strIngredient2.isNullOrBlank()) {
            ingredientes.add(Ingrediente(dto.strIngredient2, dto.strMeasure2))
        }
        if (!dto.strIngredient3.isNullOrBlank()) {
            ingredientes.add(Ingrediente(dto.strIngredient3, dto.strMeasure3))
        }

        return ingredientes
    }

    // Otros métodos (búsqueda, filtrado, etc.)
    // Buscar comidas por nombre con traducción automática
    fun buscarComidas(consulta: String): Flow<List<Receta.Comida>> = flow {
        try {
            if (consulta.isBlank()) {
                emit(emptyList())
                return@flow
            }

            // Traducir consulta automáticamente si es necesario
            val consultaTraducida = TraductorBusqueda.traducirConsulta(consulta, esComida = true)

            val respuesta = servicioComidas.buscarComidas(consultaTraducida)
            val comidas = respuesta.meals?.map { comidaDto ->
                Receta.Comida(
                    id = comidaDto.idMeal,
                    nombre = comidaDto.strMeal,
                    categoria = comidaDto.strCategory ?: "",
                    pais = comidaDto.strArea,
                    instrucciones = comidaDto.strInstructions ?: "",
                    urlImagen = comidaDto.strMealThumb ?: "",
                    ingredientes = obtenerIngredientesDeComidaDTO(comidaDto),
                    esFavorito = favoritosDao.esComidaFavorita(comidaDto.idMeal)
                )
            } ?: emptyList()

            emit(comidas)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    // Buscar bebidas por nombre con traducción automática
    fun buscarBebidas(consulta: String): Flow<List<Receta.Bebida>> = flow {
        try {
            if (consulta.isBlank()) {
                emit(emptyList())
                return@flow
            }

            // Traducir consulta automáticamente si es necesario
            val consultaTraducida = TraductorBusqueda.traducirConsulta(consulta, esComida = false)

            val respuesta = servicioBebidas.buscarBebidas(consultaTraducida)
            val bebidas = respuesta.drinks?.map { bebidaDto ->
                Receta.Bebida(
                    id = bebidaDto.idDrink,
                    nombre = bebidaDto.strDrink,
                    categoria = bebidaDto.strCategory ?: "",
                    esAlcoholica = bebidaDto.strAlcoholic?.contains("Alcoholic") ?: false,
                    tipoVaso = bebidaDto.strGlass ?: "",
                    instrucciones = bebidaDto.strInstructions ?: "",
                    urlImagen = bebidaDto.strDrinkThumb ?: "",
                    ingredientes = obtenerIngredientesDeBebidaDTO(bebidaDto),
                    esFavorito = favoritosDao.esBebidaFavorita(bebidaDto.idDrink)
                )
            } ?: emptyList()

            emit(bebidas)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    /**
     * Obtiene detalles completos de una comida específica por su ID
     */
    fun obtenerDetalleComida(id: String): Flow<Receta.Comida?> = flow {
        try {
            val respuesta = servicioComidas.obtenerDetalleComida(id)
            val comidaDto = respuesta.meals?.firstOrNull()

            if (comidaDto != null) {
                val comida = Receta.Comida(
                    id = comidaDto.idMeal,
                    nombre = comidaDto.strMeal,
                    categoria = comidaDto.strCategory ?: "",
                    pais = comidaDto.strArea,
                    instrucciones = comidaDto.strInstructions ?: "",
                    urlImagen = comidaDto.strMealThumb ?: "",
                    ingredientes = obtenerIngredientesDeComidaDTO(comidaDto),
                    esFavorito = favoritosDao.esComidaFavorita(comidaDto.idMeal)
                )
                emit(comida)
            } else {
                emit(null)
            }
        } catch (e: Exception) {
            emit(null)
        }
    }

    /**
     * Obtiene detalles completos de una bebida específica por su ID
     */
    fun obtenerDetalleBebida(id: String): Flow<Receta.Bebida?> = flow {
        try {
            val respuesta = servicioBebidas.obtenerDetalleBebida(id)
            val bebidaDto = respuesta.drinks?.firstOrNull()

            if (bebidaDto != null) {
                val bebida = Receta.Bebida(
                    id = bebidaDto.idDrink,
                    nombre = bebidaDto.strDrink,
                    categoria = bebidaDto.strCategory ?: "",
                    esAlcoholica = bebidaDto.strAlcoholic?.contains("Alcoholic") ?: false,
                    tipoVaso = bebidaDto.strGlass ?: "",
                    instrucciones = bebidaDto.strInstructions ?: "",
                    urlImagen = bebidaDto.strDrinkThumb ?: "",
                    ingredientes = obtenerIngredientesDeBebidaDTO(bebidaDto),
                    esFavorito = favoritosDao.esBebidaFavorita(bebidaDto.idDrink)
                )
                emit(bebida)
            } else {
                emit(null)
            }
        } catch (e: Exception) {
            emit(null)
        }
    }
}