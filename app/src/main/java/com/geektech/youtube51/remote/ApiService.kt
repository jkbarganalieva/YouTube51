package com.geektech.youtube51.remote

import com.geektech.youtube51.remote.model.Item
import com.geektech.youtube51.remote.model.PlayLists
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("playlists")
    fun  getPlaylists(
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("channelId") channelId: String,
    ):Call<PlayLists>

    @GET("playlistsitems")
    fun  getItemlist(
        @Query("key") key: String,
        @Query("part") part: String,
        @Query("playlistId") id: String,
    ):Call<Item>
}