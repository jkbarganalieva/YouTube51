package com.geektech.youtube51.core.ui.playlists

import androidx.lifecycle.MutableLiveData
import com.geektech.youtube51.core.App
import com.geektech.youtube51.core.base.BaseViewModel
import com.geektech.youtube51.remote.model.PlayLists

class MainViewModel : BaseViewModel() {
    fun getPlayLists(): MutableLiveData<com.geektech.youtube51.core.network.Resource<PlayLists>> {
        return App.repository.getPlayLists()
    }
}