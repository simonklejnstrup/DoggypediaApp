package com.example.doggypediaapp.ui.breedslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.doggypediaapp.R
import com.example.doggypediaapp.databinding.ItemBreedslistBinding

class BreedsListAdapter (private val breedsList: ArrayList<BreedsListModel>): RecyclerView.Adapter<BreedsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder  {
        val binding = ItemBreedslistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BreedsListAdapter.ViewHolder, position: Int) {
        holder.bind(breedsList[position])
    }

    override fun getItemCount(): Int {
        return breedsList.size
    }

    class ViewHolder(private val binding: ItemBreedslistBinding ):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: BreedsListModel) {
            binding.breedNameButton.text = model.breedName

            binding.root.setOnClickListener {
                binding.breedNameButton.text = "model.breedName"
                findNavController(itemView).navigate(R.id.breedImagesFragment)
            }
        }



    }


}