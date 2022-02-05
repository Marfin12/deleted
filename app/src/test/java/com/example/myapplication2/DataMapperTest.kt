package com.example.myapplication2.unit_test

import com.example.myapplication2.core.data.source.local.entity.TourismEntity
import com.example.myapplication2.core.data.source.remote.response.TourismResponse
import com.example.myapplication2.core.domain.model.Tourism
import com.example.myapplication2.core.utils.DataMapper.mapDomainToEntity
import com.example.myapplication2.core.utils.DataMapper.mapEntitiesToDomain
import com.example.myapplication2.core.utils.DataMapper.mapResponsesToEntities
import org.junit.Assert.assertEquals
import org.junit.Test

class DataMapperTest {
     val mockTourismEntity = TourismEntity(
         "1",
         "movie_title",
         "2021",
         "4",
         1.0,
         1.0,
         1,
         "some url",
     )

    val mockTourism = Tourism(
        "1",
        "movie_title",
        "2021",
        "4",
        1.0,
        1.0,
        1,
        "some url",
        false
    )

    val mockTourismResponse = TourismResponse(
        "1",
        "movie_title",
        "2021",
        "4",
        1.0,
        1.0,
        1,
        "some url"
    )


    @Test
    fun mapResponsesToEntities_EqualTo_ListOfTourismEntity() {
        val listTourismEntity = mapResponsesToEntities(arrayListOf(mockTourismResponse))
        assertEquals(listTourismEntity, arrayListOf(mockTourismEntity))
    }

    @Test
    fun mapDomainToEntity_EqualTo_TourismEntity() {
        val mockedTourismEntity = mapDomainToEntity(mockTourism)
        assertEquals(mockedTourismEntity, mockTourismEntity)
    }

    @Test
    fun mapEntitiesToDomain_EqualTo_ListOfTourism() {
        val mockedTourismEntity = mapEntitiesToDomain(arrayListOf(mockTourismEntity))
        assertEquals(mockedTourismEntity, arrayListOf(mockTourism))
    }
}