package com.example.doggypediaapp.api

import com.example.doggypediaapp.ui.images.ImagesModel
import com.example.doggypediaapp.ui.list.BreedsListModel
import com.example.doggypediaapp.ui.list.BreedsListRetroResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("breeds/list/all")
    suspend fun getBreedsList() : Response<BreedsListRetroResponse>


    @GET("breed/{breed}/images")
    suspend fun getImagesByBreed(@Path("breed") breed: String): Response<ImagesModel>

    @GET("breed/{breed}/{subbreed}/images")
    suspend fun getImagesBySubbreed(@Path("breed") breed: String,
                                    @Path("subbreed") subbreed: String): Response<ImagesModel>

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