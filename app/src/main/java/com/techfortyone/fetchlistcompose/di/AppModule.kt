package com.techfortyone.fetchlistcompose.di

import com.techfortyone.fetchlistcompose.BuildConfig
import com.techfortyone.fetchlistcompose.data.remote.PostApiService
import com.techfortyone.fetchlistcompose.data.remote.PostApiServiceImpl
import com.techfortyone.fetchlistcompose.data.remote.PostapiServiceHelper
import com.techfortyone.fetchlistcompose.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun provideOkhttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    } else {
        OkHttpClient.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, okHttpClient: OkHttpClient) =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(baseUrl)
            .client(okHttpClient).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).build()

    @Provides
    @Singleton
    fun providePostApiService(retrofit: Retrofit) = retrofit.create(PostApiService::class.java)


    @Provides
    @Singleton
    fun providePostApiServiceHelper(postapiServiceHelper: PostApiServiceImpl): PostapiServiceHelper =
        postapiServiceHelper
}