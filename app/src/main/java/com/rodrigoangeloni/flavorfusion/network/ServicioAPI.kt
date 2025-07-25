package com.rodrigoangeloni.flavorfusion.network

import retrofit2.http.GET
import retrofit2.http.Query

// Respuestas de API para comidas
data class RespuestaComidas(val meals: List<ComidaDTO>?)

data class ComidaDTO(
    val idMeal: String,
    val strMeal: String,
    val strCategory: String?,
    val strArea: String?,
    val strInstructions: String?,
    val strMealThumb: String?,
    val strYoutube: String?,
    val strIngredient1: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
    // ... más ingredientes
    val strMeasure1: String?,
    val strMeasure2: String?,
    val strMeasure3: String?
    // ... más medidas
)

// Respuestas de API para bebidas
data class RespuestaBebidas(val drinks: List<BebidaDTO>?)

data class BebidaDTO(
    val idDrink: String,
    val strDrink: String,
    val strCategory: String?,
    val strAlcoholic: String?,
    val strGlass: String?,
    val strInstructions: String?,
    val strDrinkThumb: String?,
    val strIngredient1: String?,
    val strIngredient2: String?,
    val strIngredient3: String?,
    // ... más ingredientes
    val strMeasure1: String?,
    val strMeasure2: String?,
    val strMeasure3: String?
    // ... más medidas
)

// API de comidas
interface ServicioAPIComidas {
    @GET("search.php")
    suspend fun buscarComidas(@Query("s") busqueda: String): RespuestaComidas

    @GET("filter.php")
    suspend fun obtenerComidasPorCategoria(@Query("c") categoria: String): RespuestaComidas

    @GET("random.php")
    suspend fun obtenerComidaAleatoria(): RespuestaComidas

    @GET("lookup.php")
    suspend fun obtenerDetalleComida(@Query("i") id: String): RespuestaComidas
}

// API de bebidas
interface ServicioAPIBebidas {
    @GET("search.php")
    suspend fun buscarBebidas(@Query("s") busqueda: String): RespuestaBebidas

    @GET("filter.php")
    suspend fun obtenerBebidasPorCategoria(@Query("c") categoria: String): RespuestaBebidas

    @GET("random.php")
    suspend fun obtenerBebidaAleatoria(): RespuestaBebidas

    @GET("lookup.php")
    suspend fun obtenerDetalleBebida(@Query("i") id: String): RespuestaBebidas
}