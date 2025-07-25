package com.rodrigoangeloni.flavorfusion.repository

import com.rodrigoangeloni.flavorfusion.database.RecetaDao
import com.rodrigoangeloni.flavorfusion.model.*
import com.rodrigoangeloni.flavorfusion.network.ServicioAPI
import com.rodrigoangeloni.flavorfusion.network.ServicioBebidas
import com.rodrigoangeloni.flavorfusion.util.ServicioTraduccion
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecetasRepositorio @Inject constructor(
    private val servicioAPI: ServicioAPI,
    private val servicioBebidas: ServicioBebidas,
    private val recetaDao: RecetaDao,
    private val servicioTraduccion: ServicioTraduccion
) {
    
    // Operaciones de API para comidas con traducción
    suspend fun buscarComidas(query: String): MealResponse? {
        return try {
            val respuesta = servicioAPI.buscarComidas(query)
            traducirRespuestaComidas(respuesta)
        } catch (e: Exception) {
            null
        }
    }
    
    suspend fun obtenerComidaAleatoria(): MealResponse? {
        return try {
            val respuesta = servicioAPI.obtenerComidaAleatoria()
            traducirRespuestaComidas(respuesta)
        } catch (e: Exception) {
            null
        }
    }
    
    suspend fun obtenerDetalleComida(id: String): MealResponse? {
        return try {
            val respuesta = servicioAPI.obtenerDetalleComida(id)
            traducirRespuestaComidas(respuesta)
        } catch (e: Exception) {
            null
        }
    }
    
    // Operaciones de API para bebidas con traducción
    suspend fun buscarBebidas(query: String): DrinkResponse? {
        return try {
            val respuesta = servicioBebidas.buscarBebidas(query)
            traducirRespuestaBebidas(respuesta)
        } catch (e: Exception) {
            null
        }
    }
    
    suspend fun obtenerBebidaAleatoria(): DrinkResponse? {
        return try {
            val respuesta = servicioBebidas.obtenerBebidaAleatoria()
            traducirRespuestaBebidas(respuesta)
        } catch (e: Exception) {
            null
        }
    }
    
    suspend fun obtenerDetalleBebida(id: String): DrinkResponse? {
        return try {
            val respuesta = servicioBebidas.obtenerDetalleBebida(id)
            traducirRespuestaBebidas(respuesta)
        } catch (e: Exception) {
            null
        }
    }
    
    // Métodos de traducción
    private suspend fun traducirRespuestaComidas(respuesta: MealResponse?): MealResponse? {
        if (respuesta?.meals == null) return respuesta

        val comidasTraducidas = respuesta.meals.map { comida ->
            comida.copy(
                strMeal = servicioTraduccion.traducir(comida.strMeal),
                strCategory = servicioTraduccion.traducirNullable(comida.strCategory),
                strArea = servicioTraduccion.traducirNullable(comida.strArea),
                strInstructions = servicioTraduccion.traducirNullable(comida.strInstructions),
                // Traducir ingredientes
                strIngredient1 = servicioTraduccion.traducirNullable(comida.strIngredient1),
                strIngredient2 = servicioTraduccion.traducirNullable(comida.strIngredient2),
                strIngredient3 = servicioTraduccion.traducirNullable(comida.strIngredient3),
                strIngredient4 = servicioTraduccion.traducirNullable(comida.strIngredient4),
                strIngredient5 = servicioTraduccion.traducirNullable(comida.strIngredient5),
                strIngredient6 = servicioTraduccion.traducirNullable(comida.strIngredient6),
                strIngredient7 = servicioTraduccion.traducirNullable(comida.strIngredient7),
                strIngredient8 = servicioTraduccion.traducirNullable(comida.strIngredient8),
                strIngredient9 = servicioTraduccion.traducirNullable(comida.strIngredient9),
                strIngredient10 = servicioTraduccion.traducirNullable(comida.strIngredient10),
                strIngredient11 = servicioTraduccion.traducirNullable(comida.strIngredient11),
                strIngredient12 = servicioTraduccion.traducirNullable(comida.strIngredient12),
                strIngredient13 = servicioTraduccion.traducirNullable(comida.strIngredient13),
                strIngredient14 = servicioTraduccion.traducirNullable(comida.strIngredient14),
                strIngredient15 = servicioTraduccion.traducirNullable(comida.strIngredient15),
                strIngredient16 = servicioTraduccion.traducirNullable(comida.strIngredient16),
                strIngredient17 = servicioTraduccion.traducirNullable(comida.strIngredient17),
                strIngredient18 = servicioTraduccion.traducirNullable(comida.strIngredient18),
                strIngredient19 = servicioTraduccion.traducirNullable(comida.strIngredient19),
                strIngredient20 = servicioTraduccion.traducirNullable(comida.strIngredient20)
            )
        }

        return MealResponse(comidasTraducidas)
    }

    private suspend fun traducirRespuestaBebidas(respuesta: DrinkResponse?): DrinkResponse? {
        if (respuesta?.drinks == null) return respuesta

        val bebidasTraducidas = respuesta.drinks.map { bebida ->
            bebida.copy(
                strDrink = servicioTraduccion.traducir(bebida.strDrink),
                strCategory = servicioTraduccion.traducirNullable(bebida.strCategory),
                strGlass = servicioTraduccion.traducirNullable(bebida.strGlass),
                strInstructions = servicioTraduccion.traducirNullable(bebida.strInstructions),
                // Traducir ingredientes
                strIngredient1 = servicioTraduccion.traducirNullable(bebida.strIngredient1),
                strIngredient2 = servicioTraduccion.traducirNullable(bebida.strIngredient2),
                strIngredient3 = servicioTraduccion.traducirNullable(bebida.strIngredient3),
                strIngredient4 = servicioTraduccion.traducirNullable(bebida.strIngredient4),
                strIngredient5 = servicioTraduccion.traducirNullable(bebida.strIngredient5),
                strIngredient6 = servicioTraduccion.traducirNullable(bebida.strIngredient6),
                strIngredient7 = servicioTraduccion.traducirNullable(bebida.strIngredient7),
                strIngredient8 = servicioTraduccion.traducirNullable(bebida.strIngredient8),
                strIngredient9 = servicioTraduccion.traducirNullable(bebida.strIngredient9),
                strIngredient10 = servicioTraduccion.traducirNullable(bebida.strIngredient10),
                strIngredient11 = servicioTraduccion.traducirNullable(bebida.strIngredient11),
                strIngredient12 = servicioTraduccion.traducirNullable(bebida.strIngredient12),
                strIngredient13 = servicioTraduccion.traducirNullable(bebida.strIngredient13),
                strIngredient14 = servicioTraduccion.traducirNullable(bebida.strIngredient14),
                strIngredient15 = servicioTraduccion.traducirNullable(bebida.strIngredient15)
            )
        }

        return DrinkResponse(bebidasTraducidas)
    }

    // Operaciones de favoritos
    fun obtenerFavoritos(): Flow<List<Receta>> {
        return recetaDao.obtenerTodosFavoritos()
    }
    
    suspend fun esFavorito(id: String): Boolean {
        return recetaDao.esFavorito(id)
    }
    
    suspend fun agregarFavorito(receta: Receta) {
        recetaDao.insertarFavorito(receta)
    }
    
    suspend fun eliminarFavorito(id: String) {
        recetaDao.eliminarFavoritoPorId(id)
    }
    
    suspend fun alternarFavorito(receta: Receta) {
        if (esFavorito(receta.id)) {
            eliminarFavorito(receta.id)
        } else {
            agregarFavorito(receta)
        }
    }
}