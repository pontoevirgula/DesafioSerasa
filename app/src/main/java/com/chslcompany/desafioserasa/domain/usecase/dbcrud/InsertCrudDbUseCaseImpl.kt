package com.chslcompany.desafioserasa.domain.usecase.dbcrud

import com.chslcompany.desafioserasa.data.db.repository.DbRepository
import com.chslcompany.desafioserasa.domain.model.Results
import javax.inject.Inject

class InsertCrudDbUseCaseImpl @Inject constructor (private val dbRepository: DbRepository): InsertCrudDbUseCase {
    override suspend fun invoke(results: Results) = dbRepository.insertCharacter(results)
}