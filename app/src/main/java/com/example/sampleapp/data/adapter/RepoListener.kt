package com.example.sampleapp.data.adapter

import com.example.sampleapp.model.ItemData

interface RepoListener {
    fun onClickListener(position:Int, item: ItemData)
}
