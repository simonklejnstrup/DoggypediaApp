package com.example.doggypediaapp.ui.breedimages

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doggypediaapp.api.ApiInterface
import com.example.doggypediaapp.databinding.FragmentBreedImagesBinding
import com.example.doggypediaapp.session.User
import com.example.doggypediaapp.ui.favourites.FavouritesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BreedImagesFragment: Fragment() {

    private lateinit var binding: FragmentBreedImagesBinding
    private val args: BreedImagesFragmentArgs by navArgs()
    @RequiresApi(Build.VERSION_CODES.N)
    private val adapter = BreedImagesAdapter() { imgUrl -> onLikeClicked(imgUrl)  }
    private val TAG = "BreedImagesFragment Log"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        if (!::binding.isInitialized) {
            binding = FragmentBreedImagesBinding.inflate(inflater)
        }
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.text = args.breedName

        binding.breedsImagesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.breedsImagesRecyclerView.adapter = adapter

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = ApiInterface.create().getImagesByBreed(args.breedName)
                withContext(Dispatchers.Main) {
                    adapter.setData(response.message)
                }
            } catch (e:Exception) {
                Log.e(TAG, "Error: ${e.message}")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun onLikeClicked(imgUrl: String) {
        val response = User.editFavourites(FavouritesModel(imgUrl, args.breedName))
        Toast.makeText(context, response, Toast.LENGTH_SHORT).show()
    }


}