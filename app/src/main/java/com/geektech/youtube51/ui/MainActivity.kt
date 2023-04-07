package com.geektech.youtube51.ui

import androidx.lifecycle.ViewModelProvider
import com.geektech.youtube51.base.BaseActivity
import com.geektech.youtube51.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    lateinit var adapter: PlayListAdapter
    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun initViewModel() {
        super.initViewModel()

        viewModel.playLists().observe(this) {
            binding.videoRv.adapter = adapter
           adapter.setList(it.items)
        }
    }

    override fun inflateViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }
}