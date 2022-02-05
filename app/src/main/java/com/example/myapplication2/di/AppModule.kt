package com.example.myapplication2.di

import com.example.myapplication2.core.domain.usecase.TourismInteractor
import com.example.myapplication2.core.domain.usecase.TourismUseCase
import com.example.myapplication2.detail.DetailTourismViewModel
import com.example.myapplication2.favorite.FavoriteViewModel
import com.example.myapplication2.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<TourismUseCase> { TourismInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
    viewModel { DetailTourismViewModel(get()) }
}