package com.example.doggypediaapp.ui.images

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.doggypediaapp.repository.ImageRepository
import com.example.doggypediaapp.ui.list.BreedsListModel

class ImagesViewModel(
    private val breedListModel: BreedsListModel
) : ViewModel() {

    private val repo = ImageRepository()
    val images: LiveData<List<String>> get() = repo.getMutableLiveData(breedListModel)

    override fun onCleared() {
        super.onCleared()
        repo.completableJob.cancel()
    }

}