package com.example.sampleapp.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.sampleapp.R
import com.example.sampleapp.databinding.CardItemLayoutBinding
import com.example.sampleapp.model.ItemData

class DataAdapter : RecyclerView.Adapter<DataAdapter.ItemViewHolder>() {
    private var itemList = ArrayList<ItemData>()
    private lateinit var cardItemListener: CardItemListener

    fun setData(data: ArrayList<ItemData>) {
        this.itemList = data
    }

    fun getData(): ArrayList<ItemData> {
        return this.itemList
    }

    fun setAdapterListener(listener: CardItemListener) {
        cardItemListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CardItemLayoutBinding.inflate(layoutInflater)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            cardItemListener.onClickListener(position, item)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ItemViewHolder(private val binding: CardItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ItemData) {
            binding.bindingText(data)
            binding.thumbImage.loadImage(data.owner.avatarUrl)
            binding.executePendingBindings()
        }

        private fun CardItemLayoutBinding.bindingText(data: ItemData) {
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
