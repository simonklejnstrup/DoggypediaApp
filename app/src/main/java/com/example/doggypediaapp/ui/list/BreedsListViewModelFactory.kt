package com.example.doggypediaapp.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BreedsListViewModelFactory
    : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return BreedsListViewModel() as T
        }
    }
