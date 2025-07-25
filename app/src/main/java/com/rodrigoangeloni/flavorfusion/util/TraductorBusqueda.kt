package com.rodrigoangeloni.flavorfusion.util

/**
 * Traductor de términos de búsqueda de español a inglés
 * para mejorar los resultados de las APIs externas
 */
object TraductorBusqueda {

    // Diccionario de traducciones comunes para comidas
    private val traduccionesComidas = mapOf(
        // Carnes
        "pollo" to "chicken",
        "carne" to "beef",
        "cerdo" to "pork",
        "pescado" to "fish",
        "salmón" to "salmon",
        "atún" to "tuna",
        "cordero" to "lamb",
        "pavo" to "turkey",
        "jamón" to "ham",
        "tocino" to "bacon",

        // Vegetales
        "tomate" to "tomato",
        "cebolla" to "onion",
        "ajo" to "garlic",
        "zanahoria" to "carrot",
        "papa" to "potato",
        "patata" to "potato",
        "apio" to "celery",
        "brócoli" to "broccoli",
        "espinaca" to "spinach",
        "lechuga" to "lettuce",
        "pepino" to "cucumber",
        "pimiento" to "pepper",
        "chile" to "chili",
        "champiñón" to "mushroom",
        "seta" to "mushroom",

        // Granos y pasta
        "arroz" to "rice",
        "pasta" to "pasta",
        "espagueti" to "spaghetti",
        "macarrones" to "macaroni",
        "fideo" to "noodle",
        "pan" to "bread",
        "harina" to "flour",
        "avena" to "oats",
        "quinoa" to "quinoa",

        // Lácteos
        "leche" to "milk",
        "queso" to "cheese",
        "mantequilla" to "butter",
        "yogur" to "yogurt",
        "crema" to "cream",
        "nata" to "cream",

        // Platos específicos
        "tacos" to "tacos",
        "pizza" to "pizza",
        "hamburguesa" to "burger",
        "sopa" to "soup",
        "ensalada" to "salad",
        "sandwich" to "sandwich",
        "empanada" to "empanada",
        "paella" to "paella",
        "risotto" to "risotto",
        "curry" to "curry",
        "sushi" to "sushi",
        "lasaña" to "lasagna",
        "tortilla" to "tortilla",
        "quesadilla" to "quesadilla",
        "burrito" to "burrito",
        "enchilada" to "enchilada",

        // Postres
        "postre" to "dessert",
        "pastel" to "cake",
        "galleta" to "cookie",
        "helado" to "ice cream",
        "chocolate" to "chocolate",
        "flan" to "flan",
        "brownie" to "brownie",
        "tarta" to "tart",
        "pie" to "pie",

        // Métodos de cocción
        "frito" to "fried",
        "asado" to "roasted",
        "hervido" to "boiled",
        "horneado" to "baked",
        "guisado" to "stewed",
        "a la plancha" to "grilled",
        "barbacoa" to "barbecue",

        // Sabores
        "dulce" to "sweet",
        "salado" to "salty",
        "picante" to "spicy",
        "agrio" to "sour",
        "amargo" to "bitter"
    )

    // Diccionario de traducciones para bebidas
    private val traduccionesBebidas = mapOf(
        // Bebidas básicas
        "agua" to "water",
        "café" to "coffee",
        "té" to "tea",
        "leche" to "milk",
        "jugo" to "juice",
        "zumo" to "juice",
        "refresco" to "soda",
        "gaseosa" to "soda",

        // Bebidas alcohólicas
        "cerveza" to "beer",
        "vino" to "wine",
        "whisky" to "whiskey",
        "vodka" to "vodka",
        "ron" to "rum",
        "tequila" to "tequila",
        "ginebra" to "gin",
        "brandy" to "brandy",
        "champán" to "champagne",
        "cava" to "champagne",

        // Cócteles populares
        "mojito" to "mojito",
        "margarita" to "margarita",
        "piña colada" to "pina colada",
        "daiquiri" to "daiquiri",
        "cosmopolitan" to "cosmopolitan",
        "manhattan" to "manhattan",
        "martini" to "martini",
        "bloody mary" to "bloody mary",
        "cuba libre" to "cuba libre",
        "caipirinha" to "caipirinha",

        // Bebidas calientes
        "chocolate caliente" to "hot chocolate",
        "café con leche" to "latte",
        "cappuccino" to "cappuccino",
        "espresso" to "espresso",
        "té verde" to "green tea",
        "té negro" to "black tea",
        "infusión" to "tea",

        // Bebidas frías
        "batido" to "smoothie",
        "milkshake" to "milkshake",
        "granizado" to "slush",
        "limonada" to "lemonade",
        "horchata" to "horchata",
        "sangría" to "sangria",

        // Jugos específicos
        "jugo de naranja" to "orange juice",
        "jugo de manzana" to "apple juice",
        "jugo de limón" to "lemon juice",
        "jugo de piña" to "pineapple juice",
        "jugo de tomate" to "tomato juice",
        "jugo de uva" to "grape juice",

        // Características
        "frío" to "cold",
        "caliente" to "hot",
        "helado" to "iced",
        "con alcohol" to "alcoholic",
        "sin alcohol" to "non-alcoholic"
    )

    /**
     * Traduce una consulta de búsqueda de español a inglés
     * @param consulta Término de búsqueda en español
     * @param esComida true para comidas, false para bebidas
     * @return Término traducido o el original si no hay traducción
     */
    fun traducirConsulta(consulta: String, esComida: Boolean = true): String {
        val consultaLimpia = consulta.lowercase().trim()
        val diccionario = if (esComida) traduccionesComidas else traduccionesBebidas

        // Buscar traducción exacta
        diccionario[consultaLimpia]?.let { return it }

        // Buscar traducción parcial (contiene la palabra)
        for ((espanol, ingles) in diccionario) {
            if (consultaLimpia.contains(espanol)) {
                return consultaLimpia.replace(espanol, ingles)
            }
        }

        // Si no hay traducción, devolver original
        return consulta
    }

    /**
     * Obtiene sugerencias de búsqueda populares
     * @param esComida true para comidas, false para bebidas
     * @return Lista de sugerencias en español
     */
    fun obtenerSugerencias(esComida: Boolean = true): List<String> {
        return if (esComida) {
            listOf(
                "pollo", "pasta", "pizza", "tacos", "hamburguesa",
                "sopa", "ensalada", "arroz", "pescado", "carne",
                "postre", "chocolate", "pastel", "sandwich", "curry"
            )
        } else {
            listOf(
                "mojito", "café", "té", "batido", "cerveza",
                "vino", "margarita", "limonada", "chocolate caliente", "cappuccino",
                "jugo de naranja", "sangría", "piña colada", "smoothie", "latte"
            )
        }
    }

    /**
     * Verifica si una consulta necesita traducción
     * @param consulta Término a verificar
     * @param esComida true para comidas, false para bebidas
     * @return true si la consulta está en español y necesita traducción
     */
    fun necesitaTraduccion(consulta: String, esComida: Boolean = true): Boolean {
        val consultaLimpia = consulta.lowercase().trim()
        val diccionario = if (esComida) traduccionesComidas else traduccionesBebidas

        return diccionario.containsKey(consultaLimpia) ||
               diccionario.keys.any { consultaLimpia.contains(it) }
    }

    /**
     * Obtiene información sobre la traducción realizada
     * @param original Consulta original
     * @param traducida Consulta traducida
     * @return Mensaje informativo para el usuario
     */
    fun obtenerMensajeTraduccion(original: String, traducida: String): String? {
        return if (original.lowercase() != traducida.lowercase()) {
            "Buscando '$traducida' (traducido de '$original')"
        } else null
    }
}
