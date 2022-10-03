package com.example.doggypediaapp.ui.favourites

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doggypediaapp.R
import com.example.doggypediaapp.databinding.FragmentFavouritesBinding

class FavouritesFragment: Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentFavouritesBinding
    @RequiresApi(Build.VERSION_CODES.N)
    private val adapter = FavouritesAdapter() { model -> onLikeClicked(model) }
    private val viewModel: FavouritesViewModel by viewModels {
        FavouritesViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        if (!::binding.isInitialized) {
            binding = FragmentFavouritesBinding.inflate(inflater)
        }
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initContent()
        setOnclickListeners()
    }

    private fun setOnclickListeners() {
        binding.apply {
            btnBack.setOnClickListener {
                findNavController().navigate(R.id.breedsListFragment) }
            searchView.setOnClickListener { searchView.isIconified = false }
        }
    }

    private fun initContent() {
        val favourites = viewModel.getFavourites()
        adapter.addData(favourites)
        binding.apply {
            favouritesRecyclerView.layoutManager = LinearLayoutManager(context)
            favouritesRecyclerView.adapter = adapter

            searchView.setOnQueryTextListener(this@FavouritesFragment)

        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun onLikeClicked(model: FavouritesModel) {
        val response = viewModel.onLikeClicked(model)
        Toast.makeText(context, response, Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onQueryTextSubmit(query: String?): Boolean {
        adapter.filter.filter(query)
        return false
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onQueryTextChange(newText: String?): Boolean {
        adapter.filter.filter(newText)
        return false
    }

}