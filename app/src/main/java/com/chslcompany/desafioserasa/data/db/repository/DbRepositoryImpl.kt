package com.chslcompany.desafioserasa.data.db.repository

import androidx.lifecycle.LiveData
import com.chslcompany.desafioserasa.data.db.datasource.CharactersDao
import com.chslcompany.desafioserasa.domain.model.Results
import javax.inject.Inject

class DbRepositoryImpl @Inject constructor(private val charactersDao: CharactersDao): DbRepository {

    override suspend fun insertCharacter(results: Results) = charactersDao.insertCharacter(results)
    override fun getAllCharacters(): LiveData<List<Results>> = charactersDao.getAllCharacters()
    override suspend fun deleteCharacter(results: Results) = charactersDao.deleteCharacter(results)

}