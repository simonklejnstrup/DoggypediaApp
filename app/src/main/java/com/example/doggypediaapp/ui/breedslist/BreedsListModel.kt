package com.example.doggypediaapp.ui.breedslist

import com.google.gson.annotations.SerializedName

data class BreedsListModel (
    @SerializedName("message")
    val breedsMap: LinkedHashMap<String, Array<String>>
    )