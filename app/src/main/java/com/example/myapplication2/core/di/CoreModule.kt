package com.example.myapplication2.core.di

import androidx.room.Room
import com.example.myapplication2.core.data.TourismRepository
import com.example.myapplication2.core.data.source.local.LocalDataSource
import com.example.myapplication2.core.data.source.local.room.MyApplicationDatabase
import com.example.myapplication2.core.data.source.remote.RemoteDataSource
import com.example.myapplication2.core.data.source.remote.network.ApiService
import com.example.myapplication2.core.domain.repository.ITourismRepository
import com.example.myapplication2.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MyApplicationDatabase>().tourismDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MyApplicationDatabase::class.java, "Tourism.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://tourism-api.dicoding.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ITourismRepository> { TourismRepository(get(), get(), get()) }
}