package com.rodrigoangeloni.flavorfusion.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoritos")
data class Receta(
    @PrimaryKey
    val id: String,
    val nombre: String,
    val imagen: String,
    val categoria: String = "",
    val area: String = "",
    val instrucciones: String = "",
    val ingredientes: String = "", // JSON string de los ingredientes
    val tipo: String, // "meal" o "drink"
    val fechaAgregado: Long = System.currentTimeMillis()
)

// DTOs para las APIs
data class MealResponse(
    val meals: List<MealDTO>?
)

data class DrinkResponse(
    val drinks: List<DrinkDTO>?
)

data class MealDTO(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String,
    val strCategory: String?,
    val strArea: String?,
    val strInstructions: String?,
    val strIngredient1: String?, val strIngredient2: String?, val strIngredient3: String?,
    val strIngredient4: String?, val strIngredient5: String?, val strIngredient6: String?,
    val strIngredient7: String?, val strIngredient8: String?, val strIngredient9: String?,
    val strIngredient10: String?, val strIngredient11: String?, val strIngredient12: String?,
    val strIngredient13: String?, val strIngredient14: String?, val strIngredient15: String?,
    val strIngredient16: String?, val strIngredient17: String?, val strIngredient18: String?,
    val strIngredient19: String?, val strIngredient20: String?,
    val strMeasure1: String?, val strMeasure2: String?, val strMeasure3: String?,
    val strMeasure4: String?, val strMeasure5: String?, val strMeasure6: String?,
    val strMeasure7: String?, val strMeasure8: String?, val strMeasure9: String?,
    val strMeasure10: String?, val strMeasure11: String?, val strMeasure12: String?,
    val strMeasure13: String?, val strMeasure14: String?, val strMeasure15: String?,
    val strMeasure16: String?, val strMeasure17: String?, val strMeasure18: String?,
    val strMeasure19: String?, val strMeasure20: String?
)

data class DrinkDTO(
    val idDrink: String,
    val strDrink: String,
    val strDrinkThumb: String,
    val strCategory: String?,
    val strGlass: String?,
    val strInstructions: String?,
    val strIngredient1: String?, val strIngredient2: String?, val strIngredient3: String?,
    val strIngredient4: String?, val strIngredient5: String?, val strIngredient6: String?,
    val strIngredient7: String?, val strIngredient8: String?, val strIngredient9: String?,
    val strIngredient10: String?, val strIngredient11: String?, val strIngredient12: String?,
    val strIngredient13: String?, val strIngredient14: String?, val strIngredient15: String?,
    val strMeasure1: String?, val strMeasure2: String?, val strMeasure3: String?,
    val strMeasure4: String?, val strMeasure5: String?, val strMeasure6: String?,
    val strMeasure7: String?, val strMeasure8: String?, val strMeasure9: String?,
    val strMeasure10: String?, val strMeasure11: String?, val strMeasure12: String?,
    val strMeasure13: String?, val strMeasure14: String?, val strMeasure15: String?
)

// Funciones de extensión para convertir DTOs a Receta
fun MealDTO.toReceta(): Receta {
    val ingredientes = mutableListOf<String>()
    val medidas = listOf(
        strMeasure1, strMeasure2, strMeasure3, strMeasure4, strMeasure5,
        strMeasure6, strMeasure7, strMeasure8, strMeasure9, strMeasure10,
        strMeasure11, strMeasure12, strMeasure13, strMeasure14, strMeasure15,
        strMeasure16, strMeasure17, strMeasure18, strMeasure19, strMeasure20
    )
    val ingredientesList = listOf(
        strIngredient1, strIngredient2, strIngredient3, strIngredient4, strIngredient5,
        strIngredient6, strIngredient7, strIngredient8, strIngredient9, strIngredient10,
        strIngredient11, strIngredient12, strIngredient13, strIngredient14, strIngredient15,
        strIngredient16, strIngredient17, strIngredient18, strIngredient19, strIngredient20
    )

    ingredientesList.forEachIndexed { index, ingrediente ->
        if (!ingrediente.isNullOrBlank()) {
            val medida = medidas.getOrNull(index) ?: ""
            ingredientes.add("$medida $ingrediente".trim())
        }
    }

    return Receta(
        id = idMeal,
        nombre = strMeal,
        imagen = strMealThumb,
        categoria = strCategory ?: "",
        area = strArea ?: "",
        instrucciones = strInstructions ?: "",
        ingredientes = ingredientes.joinToString("||"), // Separador para almacenar en DB
        tipo = "meal"
    )
}

fun DrinkDTO.toReceta(): Receta {
    val ingredientes = mutableListOf<String>()
    val medidas = listOf(
        strMeasure1, strMeasure2, strMeasure3, strMeasure4, strMeasure5,
        strMeasure6, strMeasure7, strMeasure8, strMeasure9, strMeasure10,
        strMeasure11, strMeasure12, strMeasure13, strMeasure14, strMeasure15
    )
    val ingredientesList = listOf(
        strIngredient1, strIngredient2, strIngredient3, strIngredient4, strIngredient5,
        strIngredient6, strIngredient7, strIngredient8, strIngredient9, strIngredient10,
        strIngredient11, strIngredient12, strIngredient13, strIngredient14, strIngredient15
    )

    ingredientesList.forEachIndexed { index, ingrediente ->
        if (!ingrediente.isNullOrBlank()) {
            val medida = medidas.getOrNull(index) ?: ""
            ingredientes.add("$medida $ingrediente".trim())
        }
    }

    return Receta(
        id = idDrink,
        nombre = strDrink,
        imagen = strDrinkThumb,
        categoria = strCategory ?: "",
        area = strGlass ?: "", // Para bebidas, usamos el tipo de vaso como "area"
        instrucciones = strInstructions ?: "",
        ingredientes = ingredientes.joinToString("||"),
        tipo = "drink"
    )
}

// Función para obtener los ingredientes como lista
fun Receta.getIngredientesList(): List<String> {
    return if (ingredientes.isBlank()) emptyList() else ingredientes.split("||")
}
