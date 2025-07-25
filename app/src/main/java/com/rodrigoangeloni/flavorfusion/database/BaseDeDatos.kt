package com.rodrigoangeloni.flavorfusion.database

import androidx.room.*
import com.rodrigoangeloni.flavorfusion.model.Ingrediente
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow

// Entidad para comidas favoritas
@Entity(tableName = "comidas_favoritas")
data class ComidaFavoritaEntidad(
    @PrimaryKey val id: String,
    val nombre: String,
    val categoria: String,
    val pais: String?,
    val instrucciones: String,
    val urlImagen: String,
    val ingredientes: String // JSON de ingredientes
)

// Entidad para bebidas favoritas
@Entity(tableName = "bebidas_favoritas")
data class BebidaFavoritaEntidad(
    @PrimaryKey val id: String,
    val nombre: String,
    val categoria: String,
    val esAlcoholica: Boolean,
    val tipoVaso: String,
    val instrucciones: String,
    val urlImagen: String,
    val ingredientes: String // JSON de ingredientes
)

// Convertidores para Room
class Convertidores {
    @TypeConverter
    fun deListaAJson(ingredientes: List<Ingrediente>): String {
        return Gson().toJson(ingredientes)
    }

    @TypeConverter
    fun deJsonALista(json: String): List<Ingrediente> {
        val tipo = object : TypeToken<List<Ingrediente>>() {}.type
        return Gson().fromJson(json, tipo)
    }
}

// DAO para favoritos
@Dao
interface FavoritosDao {
    @Query("SELECT * FROM comidas_favoritas")
    fun obtenerComidasFavoritas(): Flow<List<ComidaFavoritaEntidad>>

    @Query("SELECT * FROM bebidas_favoritas")
    fun obtenerBebidasFavoritas(): Flow<List<BebidaFavoritaEntidad>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarComidaFavorita(comida: ComidaFavoritaEntidad)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarBebidaFavorita(bebida: BebidaFavoritaEntidad)

    @Query("DELETE FROM comidas_favoritas WHERE id = :comidaId")
    suspend fun eliminarComidaFavorita(comidaId: String)

    @Query("DELETE FROM bebidas_favoritas WHERE id = :bebidaId")
    suspend fun eliminarBebidaFavorita(bebidaId: String)

    @Query("SELECT COUNT(*) > 0 FROM comidas_favoritas WHERE id = :comidaId")
    suspend fun esComidaFavorita(comidaId: String): Boolean

    @Query("SELECT COUNT(*) > 0 FROM bebidas_favoritas WHERE id = :bebidaId")
    suspend fun esBebidaFavorita(bebidaId: String): Boolean
}

// Base de datos principal
@Database(
    entities = [ComidaFavoritaEntidad::class, BebidaFavoritaEntidad::class],
    version = 1
)
@TypeConverters(Convertidores::class)
abstract class FlavorFusionDB : RoomDatabase() {
    abstract fun favoritosDao(): FavoritosDao
}