package com.example.doggypediaapp.ui.list

import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doggypediaapp.R
import com.example.doggypediaapp.databinding.FragmentBreedsListBinding

class BreedsListFragment : Fragment(R.layout.fragment_breeds_list) {

    private lateinit var binding: FragmentBreedsListBinding
    private val adapter = BreedsListAdapter { breedsListAdapterModel -> onButtonClicked(breedsListAdapterModel) }
    private val viewModel: BreedsListViewModel by viewModels {
        BreedsListViewModelFactory()
    }

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
        initContent()

    }

    private fun setupRecyclerView(list: ArrayList<BreedsListModel>) {
        adapter.setBreedsListItems(list)
        binding.apply {
            breedsListRecyclerView.layoutManager = LinearLayoutManager(context)
            breedsListRecyclerView.adapter = adapter
            btnFavourites.setOnClickListener { findNavController().navigate(R.id.favouritesFragment) }
        }
    }

    private fun initContent() {
        viewModel.breedmap.observe(viewLifecycleOwner) {
            val sortedBreedList = objectifyBreedmap(it)
            setupRecyclerView(sortedBreedList)
        }
    }

    private fun objectifyBreedmap(it: BreedsListRetroResponse?): ArrayList<BreedsListModel> {
        val list = ArrayList<BreedsListModel>()
        if (it != null) {
            for ((breed, subbreeds) in it.breedsMap) {
                list.add(BreedsListModel(breed))
                if (subbreeds.size > 0 ) {
                    subbreeds.forEach {subbreed -> list.add(BreedsListModel(breed, subbreed, true)) }
                }
            }
        }
        return list
    }

    private fun onButtonClicked(model: BreedsListModel) {
        val action = BreedsListFragmentDirections.actionBreedsListFragmentToBreedImagesFragment(model)
        findNavController().navigate(action)
    }



}