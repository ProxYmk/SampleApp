package com.example.sampleapp.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.sampleapp.R
import com.example.sampleapp.databinding.ListLayoutBinding
import com.example.sampleapp.model.ItemData

class DataAdapter : RecyclerView.Adapter<DataAdapter.ItemViewHolder>() {
    private var itemList = ArrayList<ItemData>()

    fun setData(data: ArrayList<ItemData>) {
        this.itemList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListLayoutBinding.inflate(layoutInflater)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ItemViewHolder(private val binding: ListLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ItemData) {
            binding.bindingText(data)
            binding.thumbImage.loadImage(data.owner.avatarUrl)
            binding.executePendingBindings()
        }

        private fun ListLayoutBinding.bindingText(data: ItemData) {
            nameTextView.text = data.name?.ifEmpty { "No Name available" }
            descTextView.text = data.description?.ifEmpty { "No Description available" }
            createdDateTextView.text = data.createdAt?.ifEmpty { "No Created date available" }
        }
    }
}

private fun ImageView.loadImage(avatarUrl: String?) {
    load(avatarUrl) {
        placeholder(R.drawable.ic_launcher_foreground)
        transformations(CircleCropTransformation())
    }
}
