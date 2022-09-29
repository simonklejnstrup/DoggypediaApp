package com.example.doggypediaapp.ui.images

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ImagesViewModelFactory(
    private val breedName: String
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ImagesViewModel(breedName) as T
    }
}