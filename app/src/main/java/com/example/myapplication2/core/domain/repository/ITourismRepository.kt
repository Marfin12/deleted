package com.example.myapplication2.core.domain.repository

import com.example.myapplication2.core.data.Resource
import com.example.myapplication2.core.domain.model.Tourism
import kotlinx.coroutines.flow.Flow

interface ITourismRepository {

    fun getAllTourism(): Flow<Resource<List<Tourism>>>

    fun getFavoriteTourism(): Flow<List<Tourism>>

    fun setFavoriteTourism(tourism: Tourism, state: Boolean)

}