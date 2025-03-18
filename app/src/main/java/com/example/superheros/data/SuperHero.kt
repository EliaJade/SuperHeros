package com.example.superheros.data

import android.widget.GridLayout.Alignment
import androidx.core.app.GrammaticalInflectionManagerCompat.GrammaticalGender
import com.google.gson.annotations.SerializedName
import java.util.concurrent.Flow.Publisher

class SuperheroResponse (
    val response: String,
    val results: List<SuperHero>
) {

}

class SuperHero (
    val id: String,
    val name: String,
    val biography: Biography,
    val image: Image,
    val work: Work,
    val appearance: Appearance,

)
class Biography (
    val publisher: String,
    @SerializedName("full-name") val realName: String,
    @SerializedName("place-of-birth") val placeOfBirth: String,
    val alignment: String

)

class Work (
    val occupation: String,
    val base: String
)

class Appearance (
    val gender: String,
    val eyeColor: String,
    val hairColor: String,
    val height: List<String>,
    val weight: List<String>,
    val race: String
) {
    fun getWeightKg(): String {
        return weight [1]
    }
    fun getHeightCm(): String {
        return height [1]
    }
}

class Image (val url : String)