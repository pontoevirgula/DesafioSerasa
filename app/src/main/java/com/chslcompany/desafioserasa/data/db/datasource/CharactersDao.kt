package com.chslcompany.desafioserasa.data.db.datasource

import androidx.lifecycle.LiveData
import androidx.room.*
import com.chslcompany.desafioserasa.domain.model.Results

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(results: Results)

    @Query("SELECT * FROM results_table")
    fun getAllCharacters(): LiveData<List<Results>>

    @Delete
    suspend fun deleteCharacter(results: Results)
}