package com.example.doggypediaapp.ui.images

import android.content.Context.MODE_PRIVATE
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doggypediaapp.databinding.FragmentBreedImagesBinding
import com.example.doggypediaapp.sharedPrefs
import com.example.doggypediaapp.ui.favourites.FavouritesModel

class ImagesFragment: Fragment() {

    private lateinit var binding: FragmentBreedImagesBinding
    private val args: ImagesFragmentArgs by navArgs()
    @RequiresApi(Build.VERSION_CODES.N)
    private val adapter = ImagesAdapter() { imgUrl -> onLikeClicked(imgUrl)  }
    private val viewModel: ImagesViewModel by viewModels {
        ImagesViewModelFactory(args.breedName)
    }
    private val TAG = "ImagesFragment Log"

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
        sharedPrefs.addImageToFavourites()

        setHeader()
        initContent()
    }

    private fun initContent() {
        viewModel.images.observe(viewLifecycleOwner, Observer {
            setupRecyclerView(it)
        })
    }

    private fun setupRecyclerView(dogs: List<String>) {
        adapter.setData(dogs)
        binding.breedsImagesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.breedsImagesRecyclerView.adapter = adapter
    }

    private fun setHeader() {
        binding.textView.text = args.breedName
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun onLikeClicked(imgUrl: String) {
        val response = sharedPrefs.editFavourites(FavouritesModel(imgUrl, args.breedName))
        Toast.makeText(context, response, Toast.LENGTH_SHORT).show()
    }


}