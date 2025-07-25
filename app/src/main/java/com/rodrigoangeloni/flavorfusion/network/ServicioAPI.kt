package com.rodrigoangeloni.flavorfusion.network

import com.rodrigoangeloni.flavorfusion.model.DrinkResponse
import com.rodrigoangeloni.flavorfusion.model.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ServicioAPI {

    // Endpoints para comidas (TheMealDB)
    @GET("search.php")
    suspend fun buscarComidas(@Query("s") query: String): MealResponse

    @GET("random.php")
    suspend fun obtenerComidaAleatoria(): MealResponse

    @GET("lookup.php")
    suspend fun obtenerDetalleComida(@Query("i") id: String): MealResponse
}

interface ServicioBebidas {

    // Endpoints para bebidas (TheCocktailDB)
    @GET("search.php")
    suspend fun buscarBebidas(@Query("s") query: String): DrinkResponse

    @GET("random.php")
    suspend fun obtenerBebidaAleatoria(): DrinkResponse

    @GET("lookup.php")
    suspend fun obtenerDetalleBebida(@Query("i") id: String): DrinkResponse
}