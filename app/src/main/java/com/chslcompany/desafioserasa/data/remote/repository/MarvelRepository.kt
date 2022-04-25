package com.chslcompany.desafioserasa.data.remote.repository

import com.chslcompany.desafioserasa.domain.model.CharactersResponse

interface MarvelRepository {
    suspend fun getCharacters(ts: Long, apiKey: String, hash: String): CharactersResponse
}