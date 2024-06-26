package com.example.techhub.domain.service

import com.example.techhub.common.objects.Constants
import com.example.techhub.data.remote.AuthApi
import com.example.techhub.data.remote.FlagApi
import com.example.techhub.data.remote.MetricasApi
import com.example.techhub.data.remote.PerfilApi
import com.example.techhub.data.remote.UsuarioApi
import com.example.techhub.domain.model.CurrentUser
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {
    private const val BASE_URL = Constants.BASE_URL

    private fun getClient(hasAuthorization: Boolean): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .apply {
                        if (hasAuthorization) {
                            val tokenInterceptor = "Bearer ${CurrentUser.userTokenJwt}"
                            addHeader("Authorization", tokenInterceptor)
                        }
                    }
                    .build()
                chain.proceed(newRequest)
            }
            .build()

    }

    private fun getRetrofitInstance(hasAuthorization: Boolean = true): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getClient(hasAuthorization))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getAuthService(): AuthApi {
        return getRetrofitInstance(false).create(AuthApi::class.java)
    }

    fun getUsuarioService(): UsuarioApi {
        return getRetrofitInstance().create(UsuarioApi::class.java)
    }

    fun getPerfilService(): PerfilApi {
        return getRetrofitInstance().create(PerfilApi::class.java)
    }

    fun getFlagService(): FlagApi {
        return getRetrofitInstance().create(FlagApi::class.java)
    }

    fun getMetricasService(): MetricasApi {
        return getRetrofitInstance().create(MetricasApi::class.java)
    }

}