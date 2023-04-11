package com.geektech.youtube51.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel:ViewModel()
{
    val loading = MutableLiveData<Boolean>()
}
