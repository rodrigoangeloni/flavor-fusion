package com.rodrigoangeloni.flavorfusion.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.rodrigoangeloni.flavorfusion.model.Receta

@Database(
    entities = [Receta::class],
    version = 1,
    exportSchema = false
)
abstract class BaseDeDatos : RoomDatabase() {

    abstract fun recetaDao(): RecetaDao

    companion object {
        @Volatile
        private var INSTANCE: BaseDeDatos? = null

        fun getDatabase(context: Context): BaseDeDatos {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BaseDeDatos::class.java,
                    "flavor_fusion_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}