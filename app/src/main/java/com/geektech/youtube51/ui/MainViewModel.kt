package com.geektech.youtube51.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geektech.youtube51.BuildConfig
import com.geektech.youtube51.base.BaseViewModel
import com.geektech.youtube51.model.PlayLists
import com.geektech.youtube51.remote.ApiService
import com.geektech.youtube51.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : BaseViewModel() {

    private val apiService: ApiService = RetrofitClient.create()
    fun playLists(): LiveData<PlayLists> {
        return getPlayLists()
    }

    private fun getPlayLists(): LiveData<PlayLists> {
        val data = MutableLiveData<PlayLists>()
        apiService.getPlaylists(BuildConfig.API_KEY, "contentDetails,snippet", "UCWOA1ZGywLbqmigxE4Qlvuw")
            .enqueue(object : Callback<PlayLists> {
                override fun onResponse(call: Call<PlayLists>, response: Response<PlayLists>) {
                    if (response.isSuccessful) {
                        data.value = response.body()
                    }
                }

                override fun onFailure(call: Call<PlayLists>, t: Throwable) {
                    print(t.stackTrace)
                    //404-not found e.t.c
                }

            })
        return data
    }
}