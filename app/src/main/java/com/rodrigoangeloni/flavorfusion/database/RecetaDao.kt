package com.rodrigoangeloni.flavorfusion.database

import androidx.room.*
import com.rodrigoangeloni.flavorfusion.model.Receta
import kotlinx.coroutines.flow.Flow

@Dao
interface RecetaDao {

    @Query("SELECT * FROM favoritos ORDER BY fechaAgregado DESC")
    fun obtenerTodosFavoritos(): Flow<List<Receta>>

    @Query("SELECT * FROM favoritos WHERE id = :id LIMIT 1")
    suspend fun obtenerRecetaPorId(id: String): Receta?

    @Query("SELECT EXISTS(SELECT 1 FROM favoritos WHERE id = :id)")
    suspend fun esFavorito(id: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarFavorito(receta: Receta)

    @Delete
    suspend fun eliminarFavorito(receta: Receta)

    @Query("DELETE FROM favoritos WHERE id = :id")
    suspend fun eliminarFavoritoPorId(id: String)

    @Query("DELETE FROM favoritos")
    suspend fun eliminarTodosFavoritos()

    @Query("SELECT COUNT(*) FROM favoritos")
    suspend fun contarFavoritos(): Int
}
