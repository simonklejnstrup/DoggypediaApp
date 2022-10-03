package com.example.doggypediaapp.ui.favourites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FavouritesViewModelFactory :
    ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FavouritesViewModel() as T
        }
    }
