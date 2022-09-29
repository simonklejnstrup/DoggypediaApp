package com.example.doggypediaapp.api

import com.example.doggypediaapp.ui.images.ImagesModel
import com.example.doggypediaapp.ui.list.BreedsListModel
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("breeds/list/all")
    fun getBreedsList() : Call<BreedsListModel>


    @GET("breed/{breedName}/images")
    suspend fun getImagesByBreed(@Path("breedName") breedName: String): Response<ImagesModel>

    companion object {

        var BASE_URL = "https://dog.ceo/api/"

        fun create() : ApiInterface {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ApiInterface::class.java)

        }
    }
}