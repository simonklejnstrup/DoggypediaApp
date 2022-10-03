package com.example.doggypediaapp.ui.list

import com.google.gson.annotations.SerializedName

data class BreedsListRetroResponse (
    @SerializedName("message")
    val breedsMap: Map<String, Array<String>>
    )