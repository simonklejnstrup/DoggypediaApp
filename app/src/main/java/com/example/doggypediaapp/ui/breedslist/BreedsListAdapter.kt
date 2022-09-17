package com.example.doggypediaapp.ui.breedslist

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.doggypediaapp.R
import com.example.doggypediaapp.databinding.ItemBreedslistBinding

class BreedsListAdapter (private val breedsList: ArrayList<BreedsListModel>,
                         private var onButtonClicked: ((breedName: String) -> Unit)
): RecyclerView.Adapter<BreedsListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder  {
        val binding = ItemBreedslistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding) {onButtonClicked(breedsList[it].breedName)}
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(breedsList[position])
    }

    override fun getItemCount(): Int {
        return breedsList.size
    }

    class ViewHolder(
        private val binding: ItemBreedslistBinding,
        private var onButtonClicked: (Int) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.breedNameButton.setOnClickListener {
                onButtonClicked(bindingAdapterPosition)

            }
        }
        fun bind(model: BreedsListModel) {

            binding.breedNameButton.text = model.breedName
        }



    }


}