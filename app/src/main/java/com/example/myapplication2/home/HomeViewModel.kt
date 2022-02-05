package com.example.myapplication2.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.myapplication2.core.domain.usecase.TourismUseCase

class HomeViewModel(tourismUseCase: TourismUseCase) : ViewModel() {
    init {
        println(tourismUseCase.getAllTourism())
    }
    val tourism = tourismUseCase.getAllTourism().asLiveData()
}

