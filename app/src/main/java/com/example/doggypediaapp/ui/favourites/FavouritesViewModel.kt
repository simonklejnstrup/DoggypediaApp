package com.example.doggypediaapp.ui.favourites

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.doggypediaapp.App.Companion.sharedPrefs

class FavouritesViewModel: ViewModel() {



    @RequiresApi(Build.VERSION_CODES.N)
    fun onLikeClicked(model: FavouritesModel): String {
        return sharedPrefs!!.editFavourites(model)
    }

    fun getFavourites(): ArrayList<FavouritesModel> {
        return sharedPrefs?.getFavouritesAsArrayList()!!
    }

}