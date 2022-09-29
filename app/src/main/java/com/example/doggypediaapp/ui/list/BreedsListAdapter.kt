package com.example.doggypediaapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doggypediaapp.databinding.ItemBreedslistBinding

class BreedsListAdapter (private var onButtonClicked: ((breedName: String) -> Unit)
): RecyclerView.Adapter<BreedsListAdapter.ViewHolder>() {

    private val breedsList = ArrayList<String>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder  {
        val binding = ItemBreedslistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding) {onButtonClicked(
            breedsList[it]
        )}
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(breedsList[position])
    }

    override fun getItemCount(): Int {
        return breedsList.size
    }

    fun setBreedsListItems(model: BreedsListModel) {
        breedsList.clear()
        for ((key, value) in model.breedsMap) {
            breedsList.add(key)
        }
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
        fun bind(breedName: String) {
            binding.breedNameButton.text = breedName
        }



    }


}