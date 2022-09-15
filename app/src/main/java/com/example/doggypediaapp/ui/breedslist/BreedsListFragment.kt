package com.example.doggypediaapp.ui.breedslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doggypediaapp.R
import com.example.doggypediaapp.databinding.FragmentBreedsListBinding


class BreedsListFragment : Fragment(R.layout.fragment_breeds_list) {

    val breedsList = arrayListOf(
        BreedsListModel(
            "Schæfer",
            emptyArray(),
        ),
        BreedsListModel(
                "Puddel",
            emptyArray(),
        ),
        BreedsListModel(
            "Doggydog",
            emptyArray(),
        )
    )

    private lateinit var binding: FragmentBreedsListBinding
    private val adapter = BreedsListAdapter(breedsList)

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

        binding.breedsListRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.breedsListRecyclerView.adapter = adapter


    }



}