package com.rodrigoangeloni.flavorfusion.repository

import com.rodrigoangeloni.flavorfusion.database.RecetaDao
import com.rodrigoangeloni.flavorfusion.model.*
import com.rodrigoangeloni.flavorfusion.network.ServicioAPI
import com.rodrigoangeloni.flavorfusion.network.ServicioBebidas
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecetasRepositorio @Inject constructor(
    private val servicioAPI: ServicioAPI,
    private val servicioBebidas: ServicioBebidas,
    private val recetaDao: RecetaDao
) {

    // Operaciones de API para comidas
    suspend fun buscarComidas(query: String): MealResponse? {
        return try {
            servicioAPI.buscarComidas(query)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun obtenerComidaAleatoria(): MealResponse? {
        return try {
            servicioAPI.obtenerComidaAleatoria()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun obtenerDetalleComida(id: String): MealResponse? {
        return try {
            servicioAPI.obtenerDetalleComida(id)
        } catch (e: Exception) {
            null
        }
    }

    // Operaciones de API para bebidas
    suspend fun buscarBebidas(query: String): DrinkResponse? {
        return try {
            servicioBebidas.buscarBebidas(query)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun obtenerBebidaAleatoria(): DrinkResponse? {
        return try {
            servicioBebidas.obtenerBebidaAleatoria()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun obtenerDetalleBebida(id: String): DrinkResponse? {
        return try {
            servicioBebidas.obtenerDetalleBebida(id)
        } catch (e: Exception) {
            null
        }
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