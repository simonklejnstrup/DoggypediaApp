package com.example.doggypediaapp.ui.images

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.doggypediaapp.ui.list.BreedsListModel


class ImagesViewModelFactory(
    private val breedsListModel: BreedsListModel
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ImagesViewModel(breedsListModel) as T
    }
}