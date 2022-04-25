package com.chslcompany.desafioserasa.domain.usecase.dbcrud

import com.chslcompany.desafioserasa.domain.model.Results

interface InsertCrudDbUseCase {
    suspend operator fun invoke(results: Results)
}