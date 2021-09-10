package com.hianuy.rickandmortyapimvvm.model

data class CharacterResponse(
    val info: Info,
    val results: List<Result>
)
