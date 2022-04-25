package com.chslcompany.desafioserasa.data.db.repository

import androidx.lifecycle.LiveData
import com.chslcompany.desafioserasa.domain.model.Results

interface DbRepository {
    suspend fun insertCharacter(results: Results)
    fun getAllCharacters(): LiveData<List<Results>>
    suspend fun deleteCharacter(results: Results)
}