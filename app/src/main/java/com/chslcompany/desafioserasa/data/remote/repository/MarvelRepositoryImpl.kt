package com.chslcompany.desafioserasa.data.remote.repository

import com.chslcompany.desafioserasa.data.remote.Api
import com.chslcompany.desafioserasa.domain.model.CharactersResponse
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(private val api: Api): MarvelRepository {
    override suspend fun getCharacters(ts: Long, apiKey: String, hash: String): CharactersResponse =
        api.getCharacters(ts, apiKey, hash)
}