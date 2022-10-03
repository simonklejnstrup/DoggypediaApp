package com.example.doggypediaapp.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.doggypediaapp.api.ApiInterface
import com.example.doggypediaapp.ui.list.BreedsListRetroResponse
import kotlinx.coroutines.*
import retrofit2.HttpException

class BreedsListRepository {
    private var mutableLiveData = MutableLiveData<BreedsListRetroResponse>()
    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)
    private val TAG = "BreedsListRepository"

    private val service by lazy {
        ApiInterface.create()
    }

    fun getMutableLiveData(): MutableLiveData<BreedsListRetroResponse> {

        coroutineScope.launch {
            val request = service.getBreedsList()
            withContext(Dispatchers.Main) {
                try {
                    if (request.body() != null) {
                        Log.d(TAG, request.body().toString())

                        mutableLiveData.value = request.body()!!

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