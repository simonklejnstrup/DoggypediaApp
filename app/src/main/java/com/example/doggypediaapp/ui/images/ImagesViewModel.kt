package com.example.doggypediaapp.ui.images

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.doggypediaapp.repository.ImageRepository

class ImagesViewModel(
    private val breedName: String
) : ViewModel() {

    private val repo = ImageRepository()
    val images: LiveData<List<String>> get() = repo.getMutableLiveData(breedName)

    override fun onCleared() {
        super.onCleared()
        repo.completableJob.cancel()
    }

}