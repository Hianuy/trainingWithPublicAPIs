package com.hianuy.rickandmortyapimvvm.data.api

import com.hianuy.rickandmortyapimvvm.model.CharacterResponse
import retrofit2.http.GET

interface RetrofitServiceAPI {

    @GET("character")
    suspend fun getAllMovies(): CharacterResponse

}