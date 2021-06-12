package com.br.events.data.network


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitService {
    companion object {
        const val BASE_URL = "http://5f5a8f24d44d640016169133.mockapi.io/api/"
    }

    private fun provideHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .apply {
                val logging = HttpLoggingInterceptor()
                logging.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(logging)
            }.build()

    var service: ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideHttpClient())
        .build()
        .create(ApiService::class.java)
}