package com.example.myapplication2.detail

import androidx.lifecycle.ViewModel
import com.example.myapplication2.core.domain.model.Tourism
import com.example.myapplication2.core.domain.usecase.TourismUseCase

class DetailTourismViewModel(private val tourismUseCase: TourismUseCase) : ViewModel() {
    fun setFavoriteTourism(tourism: Tourism, newStatus:Boolean) =
        tourismUseCase.setFavoriteTourism(tourism, newStatus)
}

