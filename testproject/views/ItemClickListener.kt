package com.karan.testproject.views

import com.karan.testproject.models.Photo

interface ItemClickListener {
    fun onItemClicked(photo: Photo)
}