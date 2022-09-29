package com.example.doggypediaapp.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.doggypediaapp.ui.favourites.FavouritesModel
import com.google.gson.Gson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class SharedPrefs(context: Context) {

    private val sharedPrefs = context.getSharedPreferences("doggypedia", Context.MODE_PRIVATE)
    private val editor = sharedPrefs.edit()
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    private val type = Types.newParameterizedType(MutableList::class.java, FavouritesModel::class.java)
    @OptIn(ExperimentalStdlibApi::class)
    val jsonAdapterList: JsonAdapter<List<FavouritesModel>> = moshi.adapter()

    val KEY_SP = "favourites"

    private val TAG = "SharedPrefs"

    @RequiresApi(Build.VERSION_CODES.N)
    fun editFavourites(model: FavouritesModel): String {
        var response = ""

        val favouritesArrayList = getFavouritesAsArrayList()


        val result = favouritesArrayList?.removeIf { favouritesModel ->
            (favouritesModel.imgUrl == model.imgUrl)
        }
        if (result == true) {
            response = "Image removed from favourites"
        } else {
            favouritesArrayList?.add(model)
            response = "Image added to  favourites"
        }
        saveArrayListToSP(favouritesArrayList as List<FavouritesModel>)
        return response

    }

    private fun saveArrayListToSP(list: List<FavouritesModel>) {
        val jsonString = jsonAdapterList.toJson(list)
        editor.clear()
        editor.putString(KEY_SP, jsonString).commit()
    }

    fun isFavourite(model: FavouritesModel): Boolean {
        val jsonString = sharedPrefs.getString(KEY_SP, "-1")
        return jsonString!!.contains(model.imgUrl)

    }

    fun getFavouritesAsArrayList(): ArrayList<FavouritesModel>? {
        val jsonString = sharedPrefs.getString(KEY_SP, "-1")
        return jsonString?.let { jsonAdapterList.fromJson(it) } as ArrayList
    }



}