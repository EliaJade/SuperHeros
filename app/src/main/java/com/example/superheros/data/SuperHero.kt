package com.example.superheros.data

class SuperheroResponse (
    val response: String,
    val results: List<SuperHero>
) {

}

class SuperHero (
    val id: String,
    val name: String,
    val image: Image,
    val result: List<SuperHero>
){
}

class Image (val url : String)