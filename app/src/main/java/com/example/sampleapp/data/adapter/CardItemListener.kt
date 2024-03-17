package com.example.sampleapp.data.adapter

import com.example.sampleapp.model.ItemData

interface CardItemListener {
    fun onClickListener(position:Int, item: ItemData)
}
