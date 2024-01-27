package com.example.covid19app.di

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun getFile(context: Context): File {
        val file = File(context.filesDir, "cache-dir")
        if (!file.exists()) file.mkdir()
        return file
    }

    @Singleton
    @Provides
    fun getCache(cache: File): Cache {
        return Cache(cache, 10 * 1000 * 1000)
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Singleton
    @Provides
    fun getCacheInterceptor(context: Context): Interceptor {
        return Interceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader(API_HOST, HOST)
                .addHeader(API_KEY, KEY)
                .build()
            val response = chain.proceed(request)
            val cacheControl = if (isConnected(context)) {
                CacheControl.Builder()
                    .maxAge(0, TimeUnit.SECONDS)
                    .build()
            } else {
                CacheControl.Builder()
                    .maxAge(7, TimeUnit.DAYS)
                    .build()
            }

            response.newBuilder()
                .removeHeader(PRAGMA_HEADER)
                .removeHeader(CACHE_CONTROL_HEADER)
                .addHeader(CACHE_CONTROL_HEADER, cacheControl.toString())
                .build()
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        cache: Cache,
        logging: HttpLoggingInterceptor,
        interceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(interceptor)
            .cache(cache)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun isConnected(context: Context): Boolean {
        try {
            val e = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = e.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        } catch (e: Exception) {
            Log.e(this.javaClass.name, "Error check isConnected", e)
        }
        return false
    }

    companion object {
        const val PRAGMA_HEADER: String = "pragma"
        const val CACHE_CONTROL_HEADER: String = "Cache-Control"
        const val BASE_URL: String = "https://covid-19-tracking.p.rapidapi.com"
        const val API_KEY: String = "X-RapidAPI-Key"
        const val API_HOST: String = "X-RapidAPI-Host"
        const val KEY: String = "0be087cfa8msh173552b5ca9d473p1324ffjsnd22a9fb9c10d"
        const val HOST: String = "covid-19-tracking.p.rapidapi.com"
    }
}
