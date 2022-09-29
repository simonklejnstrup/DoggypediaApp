package com.example.doggypediaapp.ui.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.doggypediaapp.App.Companion.sharedPrefs
import com.example.doggypediaapp.R
import com.example.doggypediaapp.databinding.ItemFavouritesBinding
import com.example.doggypediaapp.sharedprefs.SharedPrefs
import com.squareup.picasso.Picasso

class FavouritesAdapter(
private var onLikeClicked: ((model: FavouritesModel) -> Unit)
) : RecyclerView.Adapter<FavouritesAdapter.ViewHolder>(), Filterable {

    var favouritesList: ArrayList<FavouritesModel> = ArrayList()
    var favouritesListFiltered: ArrayList<FavouritesModel> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemFavouritesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding) {
            onLikeClicked(
                favouritesList[it]
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(favouritesListFiltered[position])
    }

    override fun getItemCount(): Int {
        return favouritesListFiltered.size
    }

    fun addData(list: ArrayList<FavouritesModel>) {
        favouritesList = list
        favouritesListFiltered = favouritesList
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemFavouritesBinding,
        private var onLikeClicked: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.favouritesLikeButton.setOnClickListener {
                binding.favouritesLikeButton.setImageResource(R.drawable.ic_heart_filled)

                if (binding.favouritesLikeButton.tag == "empty") {
                    binding.favouritesLikeButton.tag = "filled"
                } else {
                    binding.favouritesLikeButton.setImageResource(R.drawable.ic_heart_empty)
                    binding.favouritesLikeButton.tag = "empty"
                }
                onLikeClicked(bindingAdapterPosition)

            }
        }

        fun bind(model: FavouritesModel) {
            Picasso.get()
                .load(model.imgUrl)
                .error(R.drawable.ic_baseline_error_outline_24)
                .placeholder(R.drawable.progress_animation)
                .resize(1000, 0)
                .centerInside()
                .into(binding.favouritesImageView)

            binding.favouritesTextView.text = model.breedName

            if (sharedPrefs?.isFavourite(model) == true) {
                binding.favouritesLikeButton.setImageResource(R.drawable.ic_heart_filled)
                binding.favouritesLikeButton.tag = "filled"
            } else {
                binding.favouritesLikeButton.setImageResource(R.drawable.ic_heart_empty)
                binding.favouritesLikeButton.tag = "empty"
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) favouritesListFiltered = favouritesList else {
                    val filteredList = ArrayList<FavouritesModel>()
                    favouritesList
                        .filter { (it.breedName.contains(constraint!!)) }
                        .forEach { filteredList.add(it) }
                            favouritesListFiltered = filteredList

                }
                return FilterResults().apply { values = favouritesListFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                favouritesListFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<FavouritesModel>
                notifyDataSetChanged()
            }
        }
    }


}