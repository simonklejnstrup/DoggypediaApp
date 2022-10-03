package com.example.doggypediaapp.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.doggypediaapp.repository.BreedsListRepository

class BreedsListViewModel: ViewModel() {

    private val repo = BreedsListRepository()
    val breedmap: LiveData<BreedsListRetroResponse> get() = repo.getMutableLiveData()

    override fun onCleared() {
        super.onCleared()
        repo.completableJob.cancel()
    }


}