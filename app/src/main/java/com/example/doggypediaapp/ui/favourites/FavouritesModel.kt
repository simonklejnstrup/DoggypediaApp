package com.example.doggypediaapp.ui.favourites

data class FavouritesModel(val imgUrl: String, val breedName: String) {

    override fun equals(other: Any?): Boolean {
        return other is FavouritesModel && other.imgUrl.equals(this.imgUrl, ignoreCase = true)
    }


}