package com.geektech.youtube51.core.ui.playlists


import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.geektech.youtube51.core.base.BaseActivity
import com.geektech.youtube51.core.network.ConnectionLiveData
import com.geektech.youtube51.core.network.Status
import com.geektech.youtube51.core.ui.videos.VideosActivity
import com.geektech.youtube51.databinding.ActivityMainBinding
import com.geektech.youtube51.remote.model.Item

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(){
    private var adapter: PlayListAdapter? = null
    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun checkConnection() {
        super.checkConnection()
        var connection = ConnectionLiveData(application)
        connection.observe(this) { isConnected ->
            if (isConnected) {
                binding.rvVideo.visibility = View.VISIBLE
                binding.noInet.visibility = View.GONE
            } else {
                binding.rvVideo.visibility = View.GONE
                binding.noInet.visibility = View.VISIBLE
            }
        }
    }

    override fun initRV() {
        super.initRV()
        adapter = PlayListAdapter(this::onClick)
        binding.rvVideo.layoutManager = LinearLayoutManager(this)
    }

    override fun initViewModel() {
        super.initViewModel()
        viewModel.loading.observe(this) {
            binding.progressBar.isVisible = it
        }
        viewModel.getPlayLists().observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.rvVideo.adapter = adapter
                    it.data?.let { it1 -> adapter?.setList(it1.items) }
                    viewModel.loading.postValue(false)
                }
                Status.LOADING -> {
                    viewModel.loading.postValue(true)
                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    viewModel.loading.postValue(false)
                }
            }
        }
    }

    override fun inflateViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    fun onClick(item: Item) {
        val intent = Intent(this@MainActivity, VideosActivity::class.java)
        intent.putExtra(ID, item.id)
        Toast.makeText(this, item.id, Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

    companion object{
        const val ID="id"
    }
}