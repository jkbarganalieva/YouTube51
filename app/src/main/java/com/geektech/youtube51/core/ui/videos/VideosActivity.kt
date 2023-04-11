package com.geektech.youtube51.core.ui.videos

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.geektech.youtube51.R
import com.geektech.youtube51.core.base.BaseActivity
import com.geektech.youtube51.core.network.ConnectionLiveData
import com.geektech.youtube51.core.ui.playlists.MainViewModel
import com.geektech.youtube51.databinding.ActivityVideosBinding

class VideosActivity : BaseActivity<ActivityVideosBinding, MainViewModel>(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videos)
    }

    override val viewModel: MainViewModel by lazy { ViewModelProvider(this)[MainViewModel::class.java] }

    override fun checkConnection() {
        super.checkConnection()
        ConnectionLiveData(application).observe(this) { isConnected ->
            if (isConnected) {
                binding.content.visibility = View.VISIBLE
                binding.noInet.visibility = View.GONE
            } else {
                binding.content.visibility = View.GONE
                binding.noInet.visibility = View.VISIBLE
            }
        }
    }
    override fun inflateViewBinding(): ActivityVideosBinding {
        return ActivityVideosBinding.inflate(layoutInflater)
    }
    override fun initListener() {
        super.initListener()
        val result = intent.getStringExtra("id")
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }
}