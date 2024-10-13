package io.github.xvelx.pokemonc1.network

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.inject.Inject
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class ApiClientFactory @Inject internal constructor() {

    fun <T> create(
        baseUrl: String,
        apiClientClass: Class<T>
    ): T = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(JacksonConverterFactory.create(objectMapper))
        .client(okhttpInstance)
        .build()
        .create(apiClientClass)

    private companion object {
        val objectMapper by lazy {
            ObjectMapper().apply {
                configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
            }
        }
        val okhttpInstance by lazy {
            OkHttpClient.Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                )
                .build()
        }
    }
}
