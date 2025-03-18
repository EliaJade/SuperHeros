package com.example.superheros.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.superheros.R
import com.example.superheros.adapters.SuperheroAdapter
import com.example.superheros.data.SuperHero
import com.example.superheros.data.SuperheroService
import com.squareup.picasso.Picasso
import com.example.superheros.databinding.ActivityDetailBinding
//import com.example.superheros.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivity : AppCompatActivity() {

    //lateinit var adapter: SuperheroAdapter
    lateinit var binding: ActivityDetailBinding

    lateinit var superhero: SuperHero

    //var superheroList: List<SuperHero> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val id = intent.getStringExtra("SUPERHERO_ID")!!
        getSuperheroById(id)

        binding.navigationBar.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_biography -> {
                    binding.appearanceContent.root.visibility = View.GONE
                    binding.statsContent.root.visibility = View.GONE
                    binding.biographyContent.root.visibility = View.VISIBLE

                }

                R.id.action_stats -> {
                    binding.appearanceContent.root.visibility = View.GONE
                    binding.biographyContent.root.visibility = View.GONE
                    binding.statsContent.root.visibility = View.VISIBLE
                }

                R.id.action_appearance -> {
                    binding.statsContent.root.visibility = View.GONE
                    binding.biographyContent.root.visibility = View.GONE
                    binding.appearanceContent.root.visibility= View.VISIBLE
                }

            }
            true
        }
        binding.navigationBar.selectedItemId = R.id.action_stats
    }



//lateinit var superhero: SuperHero
    fun loadData() {
        //Toast.makeText(this, superhero.name, Toast.LENGTH_SHORT)

    //hay que rellenar la info del superheroe en pantalla
    Picasso.get().load(superhero.image.url).into(binding.pictureImageView)

    //BIOGRAPHY
    binding.biographyContent.publisherTextView.text = superhero.biography.publisher
    binding.biographyContent.placeOfBirthTextView.text = superhero.biography.placeOfBirth
    binding.biographyContent.alignmentTextView.text = superhero.biography.alignment

    binding.biographyContent.baseTextView.text = superhero.work.base
    binding.biographyContent.occupationTextView.text = superhero.work.occupation

    //APPEARANCE
    binding.appearanceContent.raceTextView.text = superhero.appearance.race
    binding.appearanceContent.genderTextView.text = superhero.appearance.gender
    binding.appearanceContent.heightTextView.text = superhero.appearance.getHeightCm()
    binding.appearanceContent.weightTextView.text = superhero.appearance.getWeightKg()
    binding.appearanceContent.eyeColorTextView.text = superhero.appearance.eyeColor
    binding.appearanceContent.hairColorTextView.text = superhero.appearance.hairColor

    //STATS
    binding.statsContent.combatTextView.text = superhero.stats.combat
    binding.statsContent.powerTextView.text = superhero.stats.power
    binding.statsContent.speedTextView.text = superhero.stats.speed
    binding.statsContent.strengthTextView.text = superhero.stats.strength
    binding.statsContent.intelligenceTextView.text = superhero.stats.intelligence
    binding.statsContent.durabilityTextView.text = superhero.stats.durability
}


fun getRetrofit(): SuperheroService {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://superheroapi.com/api/6ac2bcc51f5841c14aa2dbbc44cc5dae/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(SuperheroService::class.java)

    // lateinit var adapter: SuperheroAdapter

    //var superheroList: List<SuperHero> = listOf()
}

fun getSuperheroById(id: String) {
    CoroutineScope(Dispatchers.IO).launch {
        try {

            val service = getRetrofit()
            superhero = service.findSuperheroesById(id)


            CoroutineScope(Dispatchers.Main).launch {
                loadData()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
}
