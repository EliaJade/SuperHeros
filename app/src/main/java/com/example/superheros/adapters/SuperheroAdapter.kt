package com.example.superheros.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.superheros.R
import com.example.superheros.data.SuperHero
import com.example.superheros.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperheroAdapter(var items: List<SuperHero>, val onClick: (Int) -> Unit) : Adapter<SuperheroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        val binding = LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false)
        return SuperheroViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size
        //return items.size



    override fun onBindViewHolder(holder: SuperheroViewHolder, position: Int) {
        val superhero = items[position]
        holder.render(superhero)
        holder.itemView.setOnClickListener {
            onClick(position)
        }
    }


}

class SuperheroViewHolder (val binding: ItemSuperheroBinding) : ViewHolder(binding.root){
    //val nameTextView: TextView = view.findViewById(R.id.nameTextView)
    //val pictureImageView: ImageView = view.findViewById(R.id.pictureImageView)

    fun render(superHero: SuperHero) {
        binding.nameTextView.text = superHero.name
        Picasso.get().load(superHero.image.url).into(binding.pictureImageView)
    }

}