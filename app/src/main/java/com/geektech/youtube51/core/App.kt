package com.geektech.youtube51.core

import android.app.Application
import com.geektech.youtube51.repository.Repository

class App : Application() {
    companion object {
        val repository: Repository by lazy {
            Repository()
        }
    }
}