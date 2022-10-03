package com.example.doggypediaapp.ui.list

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BreedsListModel(
    val breed: String,
    val subbreed: String = "",
    val isSubbreed: Boolean = false
) : Parcelable
