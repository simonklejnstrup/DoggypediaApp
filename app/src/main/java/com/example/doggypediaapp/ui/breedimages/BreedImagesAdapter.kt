package com.example.doggypediaapp.ui.breedimages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.doggypediaapp.R
import com.example.doggypediaapp.databinding.ItemBreedimageslistBinding
import com.example.doggypediaapp.session.User
import com.example.doggypediaapp.ui.favourites.FavouritesModel
import com.squareup.picasso.Picasso

class BreedImagesAdapter(
    private var onLikeClicked: ((imgUrl: String) -> Unit)
) : RecyclerView.Adapter<BreedImagesAdapter.ViewHolder>() {

    private val breedImagesList = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemBreedimageslistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding) {
            onLikeClicked(
                breedImagesList[it]
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(breedImagesList[position])
    }

    override fun getItemCount(): Int {
        return breedImagesList.size
    }

    fun setData(urlList: List<String>) {
        breedImagesList.clear()
        breedImagesList.addAll(urlList)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemBreedimageslistBinding,
        private var onLikeClicked: (Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.likeButton.setOnClickListener {
                    binding.likeButton.setImageResource(R.drawable.ic_heart_filled)

                if (binding.likeButton.tag == "empty") {
                    binding.likeButton.tag = "filled"
                }
                else
                {
                    binding.likeButton.setImageResource(R.drawable.ic_heart_empty)
                    binding.likeButton.tag = "empty"
                }
                onLikeClicked(bindingAdapterPosition)

            }
        }

        fun bind(url: String) {
            Picasso.get()
                .load(url)
                .error(R.drawable.ic_baseline_error_outline_24)
                .placeholder(R.drawable.progress_animation)
                .resize(1000, 0)
                .centerInside()
                .into(binding.breedImageView)

            if (User.isFavourite(FavouritesModel(url, ""))) {
                binding.likeButton.setImageResource(R.drawable.ic_heart_filled)
                binding.likeButton.tag = "filled"
            } else {
                binding.likeButton.setImageResource(R.drawable.ic_heart_empty)
                binding.likeButton.tag = "empty"
            }
        }


    }


}
