package com.karan.testproject.views

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.karan.testproject.R
import com.karan.testproject.adapter.ImageAdapter
import com.karan.testproject.databinding.ActivityShowImageBinding
import com.karan.testproject.models.Photo
import com.karan.testproject.viewmodels.ImageViewModel
import kotlinx.android.synthetic.main.activity_show_image_.*
import kotlinx.android.synthetic.main.item_layout.view.*
import java.lang.Double.min
import java.lang.Float.max
import kotlin.math.min


class ShowImageActivity : AppCompatActivity() {
    private lateinit var activityShowImageBinding: ActivityShowImageBinding
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor = 1.0f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityShowImageBinding= DataBindingUtil.setContentView(this,R.layout.activity_show_image_)
        scaleGestureDetector = ScaleGestureDetector(this, ScaleListener())

        /**
         * Setting the data which we are getting from ItemList.
         */

        if (intent.extras != null) {
            val server_id = intent.getStringExtra("server_id")
            val secret_id = intent.getStringExtra("secret_id")
            val key_id = intent.getStringExtra("key_id")

            Glide.with(this)
              .load("https://live.staticflickr.com/" + server_id + "/" + key_id + "_" + secret_id + ".jpg")
              .into(set_image)
        }

    }


    override fun onTouchEvent(motionEvent: MotionEvent?): Boolean {
        scaleGestureDetector.onTouchEvent(motionEvent)
        return true
    }



    /**
     * Implemented image ZoomIn and ZoomOut
     */
    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        @RequiresApi(Build.VERSION_CODES.N)
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            scaleFactor *= scaleGestureDetector.scaleFactor
            scaleFactor = max(0.1f, min(scaleFactor, 10.0f))
            set_image.scaleX = scaleFactor
            set_image.scaleY = scaleFactor
            return true
        }
    }

}





