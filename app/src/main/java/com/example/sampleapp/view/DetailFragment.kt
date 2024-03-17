package com.example.sampleapp.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import coil.transform.CircleCropTransformation
import com.example.sampleapp.R
import com.example.sampleapp.databinding.FragmentDetailBinding
import com.example.sampleapp.model.ItemData
import com.example.sampleapp.viewmodel.SharedViewModel

class DetailFragment : Fragment() {

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var _binding: FragmentDetailBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.selectedItem.value?.let { item ->
            context?.let { context -> binding.bindItemData(item, context) }
        }
        binding.executePendingBindings()
    }
}

private fun FragmentDetailBinding.bindItemData(item: ItemData, context: Context) {
    with(item) {
        titleTextView.text = name
        thumbImage.loadImage(owner.avatarUrl)
        descriptionTextView.text = description

        val urlText = context.getString(R.string.url_text, url)
        val languageText = context.getString(R.string.language_text, language)
        val forksText = context.getString(R.string.forks_text, forks)

        urlTextView.text = urlText
        languageTextView.text = languageText
        forksTextView.text = forksText
    }
}

private fun ImageView.loadImage(avatarUrl: String?) {
    load(avatarUrl) {
        placeholder(R.drawable.ic_launcher_foreground)
        transformations(CircleCropTransformation())
    }
}
