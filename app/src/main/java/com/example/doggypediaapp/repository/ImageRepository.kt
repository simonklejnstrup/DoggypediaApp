package com.example.doggypediaapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.doggypediaapp.api.ApiInterface
import com.example.doggypediaapp.ui.images.ImagesModel
import kotlinx.coroutines.*
import retrofit2.HttpException

class ImageRepository() {
    private var dogs = mutableListOf<String>()
    private var mutableLiveData = MutableLiveData<List<String>>()
    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)
    private val TAG = "ImageRepository"

    private val service by lazy {
        ApiInterface.create()
    }

    fun getMutableLiveData(breedName: String):MutableLiveData<List<String>> {

        coroutineScope.launch {
            val request = service.getImagesByBreed(breedName)
            withContext(Dispatchers.Main) {
                try {
                    if (request.body() != null) {
                        Log.d(TAG, request.body().toString())
                        val urlList = request.body()!!.message
                        mutableLiveData.value = urlList!!

                    }

                } catch (e: HttpException) {
                    Log.d(TAG, e.toString())

                } catch (e: Throwable) {
                    Log.d(TAG, e.toString())
                }
            }
        }
        return mutableLiveData;

    }

}