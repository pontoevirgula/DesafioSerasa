package com.chslcompany.desafioserasa.data.db.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chslcompany.desafioserasa.domain.model.Results

@Database(entities = [Results::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CharactersDataBase: RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}