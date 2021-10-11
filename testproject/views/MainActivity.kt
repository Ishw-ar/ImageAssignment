package com.karan.testproject.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.ContactsContract
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.loader.content.Loader
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.karan.testproject.R
import com.karan.testproject.adapter.ImageAdapter
import com.karan.testproject.databinding.ActivityMainBinding
import com.karan.testproject.models.ImageResponse

import com.karan.testproject.models.Photo
import com.karan.testproject.models.Photos
import com.karan.testproject.repository.ImageRepository

import com.karan.testproject.viewmodels.ImageViewModel

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_show_image_.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), ItemClickListener{
    private lateinit var activityMainBinding: ActivityMainBinding
    private val imageViewModel by viewModel<ImageViewModel>()
    lateinit var imageAdapter: ImageAdapter
    lateinit var shimmerFrameLayout: ShimmerFrameLayout
    var isGrid:Boolean=false




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        shimmerFrameLayout= findViewById(R.id.shimmer_view)
        initMembers()
        setUpListViews()
        fetchImagesLiveData()
        btn_image.setOnClickListener {
            if (!isGrid) {
                setUpGridViews()
                isGrid = true
                btn_image.text = "Change to List View"
                fetchImagesLiveData()
            } else {
                setUpListViews()
                isGrid = false
                btn_image.text = "Change to Grid View"
                fetchImagesLiveData()
            }
        }
    }
    private fun initMembers() {
        imageAdapter = ImageAdapter(this)
    }
    private fun setUpListViews() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = imageAdapter
    }
    private fun setUpGridViews() {
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = imageAdapter
    }
    private fun fetchImagesLiveData() {
        imageViewModel.fetchImagesLiveData().observe(this, Observer {
            Handler(Looper.getMainLooper()).postDelayed({
                shimmerFrameLayout.stopShimmerAnimation()
                shimmerFrameLayout.visibility = View.GONE
                lifecycleScope.launch {
                    imageAdapter.submitData(it)
                }
            },5000)
        })
    }
    override fun onResume() {
        super.onResume()
        shimmerFrameLayout.startShimmerAnimation()
    }
    override fun onPause() {
        shimmerFrameLayout.stopShimmerAnimation()
        super.onPause()
    }

    override fun onItemClicked(photo: Photo) {
        val intent= Intent(this,ShowImageActivity::class.java)
        intent.putExtra("secret_id",photo.secret)
        intent.putExtra("server_id", photo.server)
        intent.putExtra("key_id", photo.id)
        startActivity(intent)
    }



}