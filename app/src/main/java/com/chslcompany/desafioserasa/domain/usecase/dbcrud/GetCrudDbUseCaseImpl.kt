package com.chslcompany.desafioserasa.domain.usecase.dbcrud

import androidx.lifecycle.LiveData
import com.chslcompany.desafioserasa.data.db.repository.DbRepository
import com.chslcompany.desafioserasa.domain.model.Results
import javax.inject.Inject

class GetCrudDbUseCaseImpl @Inject constructor(private val dbRepository: DbRepository): GetCrudDbUseCase {
    override fun invoke(): LiveData<List<Results>> = dbRepository.getAllCharacters()
}