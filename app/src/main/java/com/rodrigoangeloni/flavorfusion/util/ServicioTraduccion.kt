package com.rodrigoangeloni.flavorfusion.util

import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Servicio de traducción utilizando ML Kit de Google
 * Permite traducir texto del inglés al español directamente en el dispositivo
 */
@Singleton
class ServicioTraduccion @Inject constructor() {

    // Opciones para el traductor (inglés -> español)
    private val options = TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.ENGLISH)
        .setTargetLanguage(TranslateLanguage.SPANISH)
        .build()

    // Instancia del traductor
    private val traductor = Translation.getClient(options)

    // Variable para saber si el modelo ya se descargó
    private var modeloDescargado = false

    /**
     * Descarga el modelo de traducción si no está descargado
     * @return true si el modelo está listo para usar
     */
    suspend fun descargarModeloSiNecesario(): Boolean = withContext(Dispatchers.IO) {
        if (!modeloDescargado) {
            try {
                traductor.downloadModelIfNeeded().await()
                modeloDescargado = true
            } catch (e: Exception) {
                return@withContext false
            }
        }
        return@withContext modeloDescargado
    }

    /**
     * Traduce un texto del inglés al español
     * @param texto Texto en inglés a traducir
     * @return Texto traducido al español o el texto original si ocurre un error
     */
    suspend fun traducir(texto: String): String = withContext(Dispatchers.IO) {
        if (texto.isBlank()) return@withContext texto

        // Intentar descargar el modelo si es necesario
        if (!descargarModeloSiNecesario()) {
            return@withContext texto
        }

        try {
            // Traducir el texto
            val resultado = traductor.translate(texto).await()
            // Convertir primera letra a mayúscula si el texto original comienza con mayúscula
            if (texto.isNotEmpty() && texto[0].isUpperCase()) {
                return@withContext resultado.replaceFirstChar { it.uppercase() }
            }
            return@withContext resultado
        } catch (e: Exception) {
            // Si hay un error, devolver el texto original
            return@withContext texto
        }
    }

    /**
     * Traduce un texto potencialmente nulo del inglés al español
     * @param texto Texto en inglés a traducir (puede ser nulo)
     * @return Texto traducido al español, el texto original, o null si la entrada era null
     */
    suspend fun traducirNullable(texto: String?): String? {
        if (texto == null) return null
        return traducir(texto)
    }

    /**
     * Traduce una lista de textos del inglés al español
     * @param textos Lista de textos en inglés a traducir
     * @return Lista de textos traducidos al español
     */
    suspend fun traducirLista(textos: List<String>): List<String> = withContext(Dispatchers.IO) {
        if (textos.isEmpty()) return@withContext emptyList()

        // Intentar descargar el modelo si es necesario
        if (!descargarModeloSiNecesario()) {
            return@withContext textos
        }

        return@withContext textos.map { texto ->
            try {
                traducir(texto)
            } catch (e: Exception) {
                texto
            }
        }
    }

    /**
     * Libera los recursos del traductor
     */
    fun cerrar() {
        traductor.close()
        modeloDescargado = false
    }
}
