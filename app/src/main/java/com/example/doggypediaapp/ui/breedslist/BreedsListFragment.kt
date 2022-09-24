package com.example.doggypediaapp.ui.breedslist

import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doggypediaapp.R
import com.example.doggypediaapp.api.ApiInterface
import com.example.doggypediaapp.databinding.FragmentBreedsListBinding
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback


class BreedsListFragment : Fragment(R.layout.fragment_breeds_list) {

    val breedsListModel = BreedsListModel(
        linkedMapOf("african" to emptyArray(),
                    "beagle" to emptyArray())
    )

    private lateinit var binding: FragmentBreedsListBinding
    private val adapter = BreedsListAdapter { breedName -> onButtonClicked(breedName) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        if (!::binding.isInitialized) {
            binding = FragmentBreedsListBinding.inflate(inflater)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            breedsListRecyclerView.layoutManager = LinearLayoutManager(context)
            breedsListRecyclerView.adapter = adapter
            btnFavourites.setOnClickListener { findNavController().navigate(R.id.favouritesFragment) }
        }
        adapter.setBreedsListItems(breedsListModel)
    }

    fun onButtonClicked(breedName: String) {
        val action = BreedsListFragmentDirections.actionBreedsListFragmentToBreedImagesFragment(breedName)
        findNavController().navigate(action)
    }



}