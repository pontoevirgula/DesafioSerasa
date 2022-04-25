package com.chslcompany.desafioserasa.data.remote

import com.chslcompany.desafioserasa.domain.model.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("characters")
    suspend fun getCharacters(
        @Query("ts")ts: Long,
        @Query("apikey")apiKey: String,
        @Query("hash")hash: String
    ): CharactersResponse

}