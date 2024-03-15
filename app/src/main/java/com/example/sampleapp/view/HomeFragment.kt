package com.example.sampleapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampleapp.R
import com.example.sampleapp.databinding.FragmentHomeBinding
import com.example.sampleapp.network.NetworkResult
import com.example.sampleapp.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.executePendingBindings()

        binding.recyclerView.apply {
            adapter = homeViewModel.getAdapter()
            layoutManager = LinearLayoutManager(requireContext())
            val decoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
            addItemDecoration(decoration)
        }
        fetchData()
    }

    private fun fetchData() {
        homeViewModel.makeApiCall()
        homeViewModel.response.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(),
                        getString(R.string.error_fetching_data), Toast.LENGTH_LONG)
                        .show()
                }

                is NetworkResult.Loading -> {
                    binding.progressbar.visibility = View.VISIBLE
                }

                is NetworkResult.Success -> {
                    binding.progressbar.visibility = View.INVISIBLE
                    homeViewModel.setAdapterData(it.data?.items)
                }
            }
        }
    }
}