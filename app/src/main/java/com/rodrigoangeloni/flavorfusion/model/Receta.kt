package com.rodrigoangeloni.flavorfusion.model

// Clase base para recetas
sealed class Receta {
    // Receta de comida
    data class Comida(
        val id: String,
        val nombre: String,
        val categoria: String,
        val pais: String?,
        val instrucciones: String,
        val urlImagen: String,
        val ingredientes: List<Ingrediente>,
        val esFavorito: Boolean = false
    ) : Receta()

    // Receta de bebida
    data class Bebida(
        val id: String,
        val nombre: String,
        val categoria: String,
        val esAlcoholica: Boolean,
        val tipoVaso: String,
        val instrucciones: String,
        val urlImagen: String,
        val ingredientes: List<Ingrediente>,
        val esFavorito: Boolean = false
    ) : Receta()
}

// Modelo para ingredientes
data class Ingrediente(
    val nombre: String,
    val cantidad: String?
)

// Modelo para categorías
data class Categoria(
    val id: String,
    val nombre: String,
    val urlImagen: String?
)

// Modelo para países
data class Pais(
    val nombre: String
)