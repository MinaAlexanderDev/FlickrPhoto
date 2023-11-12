package com.example.moviesphoto.di

import android.content.Context
import androidx.room.Room
import com.example.moviesphoto.data.domain.IMoviesPhotoPagerUseCase
import com.example.moviesphoto.data.domain.MoviesPhotoPagerUseCase
import com.example.moviesphoto.data.local.MoviesPhotoDao
import com.example.moviesphoto.data.local.MoviesPhotoDatabase
import com.example.moviesphoto.data.remote.MoviesPhotoApi
import com.example.moviesphoto.util.Constants.DATABASE_NAME
import com.example.moviesphoto.util.Constants.FLICKER_BASE_URL
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
            .callTimeout(15, TimeUnit.SECONDS).connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS).readTimeout(15, TimeUnit.SECONDS)

        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun providesMoviesPhotoApi(okHttpClient: OkHttpClient): MoviesPhotoApi {
        return Retrofit.Builder().baseUrl(FLICKER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
            .create(MoviesPhotoApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMoviesPhotoDatabase(@ApplicationContext context: Context): MoviesPhotoDatabase =
        synchronized(context) {
            Room.databaseBuilder(
                context, MoviesPhotoDatabase::class.java, DATABASE_NAME
            ).build()
        }

    @Singleton
    @Provides
    fun provideMoviesPhotoDao(moviesDatabase: MoviesPhotoDatabase): MoviesPhotoDao =
        moviesDatabase.getMoviesPhotoDao()


    @Module
    @InstallIn(SingletonComponent::class)
    abstract class MoviesPhotoPagerUseCaseModule {

        @Binds
        abstract fun moviesPagerUseCase(
            moviesPagerUseCase: MoviesPhotoPagerUseCase
        ): IMoviesPhotoPagerUseCase
    }
}

@Module
@InstallIn(SingletonComponent::class)
object CoroutineDispatchersModule {
    @IoDispatcher
    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @DefaultDispatcher
    @Provides
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @MainDispatcher
    @Provides
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}


@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class IoDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DefaultDispatcher

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainDispatcher