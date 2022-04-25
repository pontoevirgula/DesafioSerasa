package com.chslcompany.desafioserasa.domain.usecase.getcharacters

import com.chslcompany.desafioserasa.data.remote.repository.MarvelRepository
import com.chslcompany.desafioserasa.domain.model.CharactersResponse
import javax.inject.Inject

class GetCharactersUseCaseImpl @Inject constructor(
    private val marvelRepository: MarvelRepository
) : GetCharactersUseCase {
    override suspend fun invoke(ts: Long, apiKey: String, hash: String): CharactersResponse =
        marvelRepository.getCharacters(ts, apiKey, hash)
}