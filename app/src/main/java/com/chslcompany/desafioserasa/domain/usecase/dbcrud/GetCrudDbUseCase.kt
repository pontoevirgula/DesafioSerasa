package com.chslcompany.desafioserasa.domain.usecase.dbcrud

import androidx.lifecycle.LiveData
import com.chslcompany.desafioserasa.domain.model.Results

interface GetCrudDbUseCase {
    operator fun invoke(): LiveData<List<Results>>
}