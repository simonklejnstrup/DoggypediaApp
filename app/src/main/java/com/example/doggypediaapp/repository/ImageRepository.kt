package com.example.doggypediaapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.doggypediaapp.api.ApiInterface
import com.example.doggypediaapp.ui.images.ImagesModel
import com.example.doggypediaapp.ui.list.BreedsListModel
import kotlinx.coroutines.*
import retrofit2.HttpException
import retrofit2.Response

class ImageRepository() {
    private var mutableLiveData = MutableLiveData<List<String>>()
    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)
    private val TAG = "ImageRepository"

    private val service by lazy {
        ApiInterface.create()
    }

    fun getMutableLiveData(model: BreedsListModel):MutableLiveData<List<String>> {

        coroutineScope.launch {
            var request: Response<ImagesModel>
            if (model.subbreed.isNotEmpty()) {
                request = service.getImagesBySubbreed(model.breed, model.subbreed)
            } else {
                request = service.getImagesByBreed(model.breed)
            }

            withContext(Dispatchers.Main) {
                try {
                    if (request.body() != null) {

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