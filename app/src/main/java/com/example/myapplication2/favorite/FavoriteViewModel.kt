package com.example.myapplication2.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.myapplication2.core.domain.usecase.TourismUseCase

class FavoriteViewModel(tourismUseCase: TourismUseCase) : ViewModel() {
    val favoriteTourism = tourismUseCase.getFavoriteTourism().asLiveData()
}

