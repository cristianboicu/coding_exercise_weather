package com.cristianboicu.codingexerciseweather.di

import android.content.Context
import androidx.room.Room
import com.cristianboicu.codingexerciseweather.data.local.ForecastDao
import com.cristianboicu.codingexerciseweather.data.local.ForecastDatabase
import com.cristianboicu.codingexerciseweather.data.local.LocalDataSource
import com.cristianboicu.codingexerciseweather.data.remote.RemoteDataSource
import com.cristianboicu.codingexerciseweather.data.remote.WeatherServiceApi
import com.cristianboicu.codingexerciseweather.data.repository.DefaultRepository
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
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
        context.applicationContext,
        ForecastDatabase::class.java,
        "forecast"
    ).build()

    @Singleton
    @Provides
    fun provideDao(db: ForecastDatabase) = db.getForecastDao()

    @Singleton
    @Provides
    fun provideLocalDataSource(dao: ForecastDao): LocalDataSource =
        LocalDataSource(dao)

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        weatherServiceApi: WeatherServiceApi,
    ): RemoteDataSource = RemoteDataSource(weatherServiceApi)

    @Singleton
    @Provides
    fun provideDefaultRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
    ): DefaultRepository = DefaultRepository(localDataSource, remoteDataSource)

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiClient(retrofit: Retrofit): WeatherServiceApi {
        return retrofit.create(WeatherServiceApi::class.java)
    }
}