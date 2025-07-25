package com.rodrigoangeloni.flavorfusion.di

import android.content.Context
import androidx.room.Room
import com.rodrigoangeloni.flavorfusion.database.FlavorFusionDB
import com.rodrigoangeloni.flavorfusion.database.FavoritosDao
import com.rodrigoangeloni.flavorfusion.network.ServicioAPIBebidas
import com.rodrigoangeloni.flavorfusion.network.ServicioAPIComidas
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Proveer Gson
    @Provides
    @Singleton
    fun proveerGson(): Gson = Gson()

    // Proveer API de comidas
    @Provides
    @Singleton
    fun proveerServicioAPIComidas(): ServicioAPIComidas {
        return Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ServicioAPIComidas::class.java)
    }

    // Proveer API de bebidas
    @Provides
    @Singleton
    fun proveerServicioAPIBebidas(): ServicioAPIBebidas {
        return Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ServicioAPIBebidas::class.java)
    }

    // Proveer base de datos
    @Provides
    @Singleton
    fun proveerBaseDeDatos(@ApplicationContext contexto: Context): FlavorFusionDB {
        return Room.databaseBuilder(
            contexto,
            FlavorFusionDB::class.java,
            "flavor_fusion_db"
        ).build()
    }

    // Proveer DAO
    @Provides
    @Singleton
    fun proveerFavoritosDao(db: FlavorFusionDB): FavoritosDao {
        return db.favoritosDao()
    }
}