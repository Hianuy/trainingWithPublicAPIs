package com.hianuy.rickandmortyapimvvm.repository

import com.hianuy.rickandmortyapimvvm.data.api.RetrofitServiceAPI
import com.hianuy.rickandmortyapimvvm.model.CharacterResponse

class CharacterRepository(private val retrofitServiceAPI: RetrofitServiceAPI) {

    suspend fun getCharacterService(): CharacterResponse = retrofitServiceAPI.getAllMovies()
}

