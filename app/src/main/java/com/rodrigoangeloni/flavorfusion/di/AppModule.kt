package com.rodrigoangeloni.flavorfusion.di

import android.content.Context
import androidx.room.Room
import com.rodrigoangeloni.flavorfusion.database.BaseDeDatos
import com.rodrigoangeloni.flavorfusion.database.RecetaDao
import com.rodrigoangeloni.flavorfusion.network.ServicioAPI
import com.rodrigoangeloni.flavorfusion.network.ServicioBebidas
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MealRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DrinkRetrofit

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // Retrofit para comidas (TheMealDB)
    @Provides
    @Singleton
    @MealRetrofit
    fun provideMealRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Retrofit para bebidas (TheCocktailDB)
    @Provides
    @Singleton
    @DrinkRetrofit
    fun provideDrinkRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Servicio API para comidas
    @Provides
    @Singleton
    fun provideServicioAPI(@MealRetrofit retrofit: Retrofit): ServicioAPI {
        return retrofit.create(ServicioAPI::class.java)
    }

    // Servicio API para bebidas
    @Provides
    @Singleton
    fun provideServicioBebidas(@DrinkRetrofit retrofit: Retrofit): ServicioBebidas {
        return retrofit.create(ServicioBebidas::class.java)
    }

    // Proveer base de datos
    @Provides
    @Singleton
    fun provideBaseDeDatos(@ApplicationContext context: Context): BaseDeDatos {
        return BaseDeDatos.getDatabase(context)
    }

    // Proveer DAO
    @Provides
    fun provideRecetaDao(baseDeDatos: BaseDeDatos): RecetaDao {
        return baseDeDatos.recetaDao()
    }

    // Proveer servicio de traducción
    @Provides
    @Singleton
    fun provideServicioTraduccion(): com.rodrigoangeloni.flavorfusion.util.ServicioTraduccion {
        return com.rodrigoangeloni.flavorfusion.util.ServicioTraduccion()
    }
}