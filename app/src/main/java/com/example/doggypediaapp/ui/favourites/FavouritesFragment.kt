package com.example.doggypediaapp.ui.favourites

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doggypediaapp.App.Companion.sharedPrefs
import com.example.doggypediaapp.databinding.FragmentFavouritesBinding

class FavouritesFragment: Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentFavouritesBinding
    @RequiresApi(Build.VERSION_CODES.N)
    private val adapter = FavouritesAdapter() { model -> onLikeClicked(model) }

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

        binding.favouritesRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.favouritesRecyclerView.adapter = adapter
        binding.searchView.setOnQueryTextListener(this)

        if (com.example.doggypediaapp.sharedPrefs.getFavouritesAsArrayList()!!.size > 0) {
            com.example.doggypediaapp.sharedPrefs.getFavouritesAsArrayList()
                ?.let { adapter.addData(it) }

        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun onLikeClicked(model: FavouritesModel) {
        val response = com.example.doggypediaapp.sharedPrefs.editFavourites(model)
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