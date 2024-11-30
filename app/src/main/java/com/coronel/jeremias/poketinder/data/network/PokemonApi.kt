package com.coronel.jeremias.poketinder.data.network

import com.coronel.jeremias.poketinder.data.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET

interface PokemonApi {

    @GET("/api/v2/pokemon")
    suspend fun getPokemons(): Response<PokemonListResponse>

}