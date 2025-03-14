package com.example.superheros.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.superheros.R
import com.example.superheros.adapters.SuperheroAdapter
import com.example.superheros.data.SuperHero
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {

        find
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val id = intent.getStringExtra("SUPERHERO_IO")!!
        getSuperheroById(id)
    }

    fun loadData() {
            //hay que rellenar la info del superheroe en pantalla

    }    }

    fun getRetrofit(query: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://superheroapi.com/api/6ac2bcc51f5841c14aa2dbbc44cc5dae/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        lateinit var adapter: SuperheroAdapter

        var superheroList: List<SuperHero> = listOf()

    fun getSuperheroById(id:String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {

                val service = getRetrofit(id)
                val result = service.findSuperheroesById(id)

                CoroutineScope(Dispatchers.Main).launch {
                    adapter.items = superheroList
                    adapter.notifyDataSetChanged()
                }

            } catch (e: Exception) {
                e.printStackTrace()
    }
}