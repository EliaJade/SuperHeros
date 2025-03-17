package com.example.superheros.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.superheros.R
import com.example.superheros.adapters.SuperheroAdapter
import com.example.superheros.data.SuperHero
import com.example.superheros.data.SuperheroService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: SuperheroAdapter

    var superheroList: List<SuperHero> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerView)



        adapter = SuperheroAdapter(superheroList) { position ->
            //code that's excuted when clicking on superhero
            val superhero = superheroList[position]

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("SUPERHERO_ID", superhero.id)
            startActivity(intent)

            //TESTS THE CLICK:
            //Toast.makeText(this, superhero.name, Toast.LENGTH_SHORT).show()

        }

        recyclerView.adapter = adapter

        recyclerView.layoutManager = GridLayoutManager(this, 2)


        searchSuperheroesByName("a")
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activities_main, menu)

        val menuItem = menu?.findItem(R.id.action_search)
        val searchView = menuItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                //Log.i("MENU", "I pressed Enter")
                searchSuperheroesByName(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {

                return true
            }
        })

        return true
    }


    fun getRetrofit(): SuperheroService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://superheroapi.com/api/6ac2bcc51f5841c14aa2dbbc44cc5dae/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(SuperheroService::class.java)
    }

    fun searchSuperheroesByName(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val service = getRetrofit()
                val result = service.findSuperheroesByName(query)

                superheroList = result.results

                CoroutineScope(Dispatchers.Main).launch {
                    adapter.items = superheroList
                    adapter.notifyDataSetChanged()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }

            /*for (superhero in result.results) {
                Log.i("API", "${superhero.id} -> ${superhero.name}")*/
        }
    }

}
