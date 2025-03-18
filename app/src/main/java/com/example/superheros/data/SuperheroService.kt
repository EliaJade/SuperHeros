package com.example.superheros.data

import retrofit2.http.GET
import retrofit2.http.Path

interface SuperheroService {

    //anotaciones
    @GET("search/{name}")

    suspend fun findSuperheroesByName(@Path("name") query: String): SuperheroResponse

    @GET("{superhero_id}")
    suspend fun findSuperheroesById(@Path("superhero_id") id: String) : SuperHero

}