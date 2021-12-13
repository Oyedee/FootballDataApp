package com.example.footballdataapp.di

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room
import com.example.footballdataapp.api.CompetitionApi
import com.example.footballdataapp.data.CompetitionDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides fun loggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun client(): OkHttpClient = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
        val newRequest: Request = chain.request().newBuilder()
            .addHeader("X-Auth-Token", "f0c8e1bf50a945f29b5de81783978001")
            .build()
        chain.proceed(newRequest)
    }).addInterceptor(loggingInterceptor()).build()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(CompetitionApi.BASE_URL)
            .client(client())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideCompetitionApi(retrofit: Retrofit): CompetitionApi =
        retrofit.create(CompetitionApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): CompetitionDatabase =
        Room.databaseBuilder(app, CompetitionDatabase::class.java, "competition_database")
            .build()
}