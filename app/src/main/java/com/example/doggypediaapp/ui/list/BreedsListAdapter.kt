package com.example.doggypediaapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doggypediaapp.databinding.ItemBreedslistBreedBinding
import com.example.doggypediaapp.databinding.ItemBreedslistSubbreedBinding
import com.example.doggypediaapp.ui.list.BreedsListAdapter.Const.ISBREED
import com.example.doggypediaapp.ui.list.BreedsListAdapter.Const.ISSUBBREED

class BreedsListAdapter (private var onButtonClicked: ((model: BreedsListModel) -> Unit)
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val breedsList = ArrayList<BreedsListModel>()

    override fun getItemViewType(position: Int): Int {
        return if (breedsList[position].isSubbreed) ISSUBBREED else ISBREED
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder  {
        return if (viewType == ISBREED){
            val binding = ItemBreedslistBreedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ViewHolderBreed(binding) {onButtonClicked(
                breedsList[it]
            )}
        } else {
            val binding = ItemBreedslistSubbreedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            ViewHolderSubbreed(binding) {onButtonClicked(
                breedsList[it]
            )}
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ISBREED){
            (holder as ViewHolderBreed).bind(breedsList[position].breed.replaceFirstChar(Char::uppercase))
        } else{
            (holder as ViewHolderSubbreed).bind(breedsList[position].subbreed.replaceFirstChar(Char::uppercase))
        }
    }

    override fun getItemCount(): Int {
        return breedsList.size
    }

    fun setBreedsListItems(list: ArrayList<BreedsListModel>) {
        breedsList.clear()
        breedsList.addAll(list)
    }

    inner class ViewHolderBreed(
        private val binding: ItemBreedslistBreedBinding,
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

    inner class ViewHolderSubbreed(
        private val binding: ItemBreedslistSubbreedBinding,
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

    private object Const{
        const val ISSUBBREED = 0
        const val ISBREED = 1
    }


}