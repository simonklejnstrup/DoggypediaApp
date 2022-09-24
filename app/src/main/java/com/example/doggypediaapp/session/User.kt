package com.example.doggypediaapp.session

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.doggypediaapp.ui.favourites.FavouritesModel

object User {
    private val favourites = ArrayList<FavouritesModel>()

    @RequiresApi(Build.VERSION_CODES.N)
    fun editFavourites(model: FavouritesModel): String {
        var response = ""
        val result = favourites.removeIf { favouritesModel ->
            (favouritesModel.imgUrl == model.imgUrl)
        }
        if (result) {
            response = "Image removed from favourites"
        } else {
            favourites.add(model)
            response = "Image added to  favourites"
        }
        return response

    }

    fun isFavourite(model: FavouritesModel): Boolean {
        return favourites.contains(model)
    }

    fun getAllFavourites(): ArrayList<FavouritesModel> {
        return favourites
    }

}