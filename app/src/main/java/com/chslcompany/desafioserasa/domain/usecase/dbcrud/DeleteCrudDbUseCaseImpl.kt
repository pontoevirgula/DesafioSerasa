package com.chslcompany.desafioserasa.domain.usecase.dbcrud

import com.chslcompany.desafioserasa.data.db.repository.DbRepository
import com.chslcompany.desafioserasa.domain.model.Results
import javax.inject.Inject

class DeleteCrudDbUseCaseImpl @Inject constructor(private val dbRepository: DbRepository): DeleteCrudDbUseCase {
    override suspend fun deleteCharactersDb(results: Results) = dbRepository.deleteCharacter(results)
}