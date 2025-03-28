package com.example.superheros.data

import android.widget.GridLayout.Alignment
import androidx.core.app.GrammaticalInflectionManagerCompat.GrammaticalGender
import com.example.superheros.R
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
    @SerializedName("powerstats") val stats: Stats




){  fun getColorAlignment (): Int {
        return when (biography.alignment) {
            "good" -> R.color.alignment_color_good
            "bad" -> R.color.alignment_color_bad
            else -> R.color.alignment_color_neutral
        }
    }
}
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

class Stats (
    val intelligence: String,
    val strength: String,
    val speed: String,
    val durability: String,
    val power: String,
    val combat: String
) /*{
    fun getIntelligence(): String {
        if (intelligence == null) {
            return ("Unknown")
        }
    }
}*/

class Appearance (
    val gender: String,
    @SerializedName("eye-color") val eyeColor: String,
    @SerializedName("hair-color") val hairColor: String,
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