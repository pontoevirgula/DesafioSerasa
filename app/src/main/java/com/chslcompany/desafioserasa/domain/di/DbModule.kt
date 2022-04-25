package com.chslcompany.desafioserasa.domain.di

import android.content.Context
import androidx.room.Room
import com.chslcompany.desafioserasa.data.db.datasource.CharactersDao
import com.chslcompany.desafioserasa.data.db.datasource.CharactersDataBase
import com.chslcompany.desafioserasa.data.db.repository.DbRepository
import com.chslcompany.desafioserasa.data.db.repository.DbRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Singleton
    @Provides
    fun providerCharactersDataBase(@ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        CharactersDataBase::class.java,
        "characters_db"
    ).build()

    @Singleton
    @Provides
    fun providerCharactersDao(db: CharactersDataBase) = db.charactersDao()

    @Singleton
    @Provides
    fun providerDbRepository(charactersDao: CharactersDao): DbRepository
            = DbRepositoryImpl(charactersDao)

}