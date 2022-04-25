package com.chslcompany.desafioserasa.domain.usecase.getcharacters

import com.chslcompany.desafioserasa.domain.model.CharactersResponse

interface GetCharactersUseCase {
    suspend operator fun invoke(ts: Long, apiKey: String, hash: String): CharactersResponse
}