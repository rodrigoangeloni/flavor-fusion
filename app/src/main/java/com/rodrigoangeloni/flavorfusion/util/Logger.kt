package com.rodrigoangeloni.flavorfusion.util

import android.util.Log

/**
 * Sistema de logging centralizado para la aplicación
 * En producción solo loggea errores críticos, en debug loggea todo
 */
object Logger {
    private const val TAG_PREFIX = "FlavorFusion_"

    // Determinamos si estamos en debug basado en si el logging está habilitado
    private val isDebugMode: Boolean
        get() = Log.isLoggable(TAG_PREFIX + "DEBUG", Log.DEBUG)

    fun d(tag: String, message: String) {
        if (isDebugMode) {
            Log.d(TAG_PREFIX + tag, message)
        }
    }

    fun i(tag: String, message: String) {
        if (isDebugMode) {
            Log.i(TAG_PREFIX + tag, message)
        }
    }

    fun w(tag: String, message: String, throwable: Throwable? = null) {
        if (isDebugMode || throwable != null) {
            Log.w(TAG_PREFIX + tag, message, throwable)
        }
    }

    fun e(tag: String, message: String, throwable: Throwable? = null) {
        // Los errores siempre se loggean, incluso en producción
        Log.e(TAG_PREFIX + tag, message, throwable)
    }

    /**
     * Loggea eventos de red para debugging
     */
    fun network(message: String, isError: Boolean = false) {
        if (isDebugMode) {
            if (isError) {
                e("Network", message)
            } else {
                d("Network", message)
            }
        }
    }

    /**
     * Loggea eventos de base de datos
     */
    fun database(message: String, isError: Boolean = false) {
        if (isDebugMode) {
            if (isError) {
                e("Database", message)
            } else {
                d("Database", message)
            }
        }
    }
}
