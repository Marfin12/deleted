package com.example.myapplication2.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication2.core.data.source.local.entity.TourismEntity

@Database(entities = [TourismEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MyApplicationDatabase : RoomDatabase() {
    abstract fun tourismDao(): TourismDao
}