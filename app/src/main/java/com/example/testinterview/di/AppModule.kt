package com.example.testinterview.di

import android.content.Context
import com.example.testinterview.repository.TestInterviewRepository
import com.example.testinterview.repository.api.RemoteTestInterviewResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
//@InstallIn(ViewModelComponent::class)
object AppModule {
    @Provides
    fun provideBaseUrl() = ""


//    @Singleton
//    @Provides
//    fun provideRetrofitInstance(okHttpClient: OkHttpClient): RemoteTestInterviewResponse =
//        Retrofit.Builder()
//            .baseUrl(provideBaseUrl())
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(RemoteTestInterviewResponse::class.java)

    @Singleton
    @Provides
    fun provideTestInterviewRepository(@ApplicationContext context: Context): TestInterviewRepository {
        return TestInterviewRepository(context) }

    @Singleton
    @Provides
    internal fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 1

        return OkHttpClient.Builder()
            .connectTimeout(timeout = 30L, TimeUnit.SECONDS)
            .readTimeout(timeout = 30L, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .retryOnConnectionFailure(retryOnConnectionFailure = true)
            .build()
    }

}