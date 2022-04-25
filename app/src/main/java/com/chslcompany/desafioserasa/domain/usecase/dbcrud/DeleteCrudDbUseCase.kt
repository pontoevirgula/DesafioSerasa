package com.chslcompany.desafioserasa.domain.usecase.dbcrud

import com.chslcompany.desafioserasa.domain.model.Results

interface DeleteCrudDbUseCase {
    suspend fun deleteCharactersDb(results: Results)
}