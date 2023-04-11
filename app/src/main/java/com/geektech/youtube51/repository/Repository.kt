package com.geektech.youtube51.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geektech.youtube51.BuildConfig
import com.geektech.youtube51.core.network.Resource
import com.geektech.youtube51.core.network.RetrofitClient
import com.geektech.youtube51.remote.ApiService
import com.geektech.youtube51.remote.model.Item
import com.geektech.youtube51.remote.model.PlayLists
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {
    private val apiService: ApiService = RetrofitClient.create()

    fun getPlayLists(): MutableLiveData<Resource<PlayLists>> {
        val data = MutableLiveData<Resource<PlayLists>>()
        data.value = Resource.loading()
        apiService.getPlaylists(
            BuildConfig.API_KEY,
            "contentDetails,snippet",
            "UCWOA1ZGywLbqmigxE4Qlvuw"
        ).enqueue(object : Callback<PlayLists> {
            override fun onResponse(call: Call<PlayLists>, response: Response<PlayLists>) {
                if (response.isSuccessful) {
                    data.value = Resource.success(response.body())
                }
            }
            override fun onFailure(call: Call<PlayLists>, t: Throwable) {
                data.value = Resource.error(t.message, null, t.hashCode())
            }
        })
        return data
    }

    fun getItemList(id:String):LiveData<Item>{
        val data=MutableLiveData<Item>()
        apiService.getItemlist(
            BuildConfig.API_KEY, "contentDetails,snippet", id)
            .enqueue(object :Callback<Item>{
                override fun onResponse(call: Call<Item>, response: Response<Item>) {
                    if (response.isSuccessful){
                        data.value=response.body()
                    }
                }

                override fun onFailure(call: Call<Item>, t: Throwable) {
                    print(t.stackTrace)
                }

            }
            )
        return data
    }

}