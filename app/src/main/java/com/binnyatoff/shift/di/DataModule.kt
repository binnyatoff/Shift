package com.binnyatoff.shift.di

import com.binnyatoff.shift.data.network.Api
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun providesRetrofit(httpLoggingInterceptor: HttpLoggingInterceptor):Retrofit{
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net/")
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    fun provideApi(retrofit: Retrofit): Api =
        retrofit.create(Api::class.java)
}