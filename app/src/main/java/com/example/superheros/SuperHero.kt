package com.example.superheros

class SuperheroResponse (
    val response: String,
    val results: List<SuperHero>
) {

}

class SuperHero (
   val id: String,
    val name: String
){
}