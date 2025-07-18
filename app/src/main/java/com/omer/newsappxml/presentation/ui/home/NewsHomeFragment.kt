package com.omer.newsappxml.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.omer.newsappxml.BuildConfig
import com.omer.newsappxml.R
import com.omer.newsappxml.databinding.FragmentNewsHomeBinding
import com.omer.newsappxml.presentation.base.BaseFragment
import com.omer.newsappxml.presentation.viewmodel.NewsHomeViewModel
import com.omer.newsappxml.presentation.viewmodel.NewsUiState
import com.omer.newsappxml.util.SpinnerUtils
import com.omer.newsappxml.util.clear
import com.omer.newsappxml.util.hide
import com.omer.newsappxml.util.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsHomeFragment : BaseFragment<FragmentNewsHomeBinding>() {

    private val viewModel: NewsHomeViewModel by viewModels()
    private val newsRecyclerAdapter = NewsRecyclerAdapter(arrayListOf())

    private val selectedCountry: String
        get() = binding.countrySpinner.selectedItem as? String ?: "us"
    private val selectedCategory: String
        get() = binding.filterSpinner.selectedItem as? String ?: "general"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNewsHomeBinding {
        return FragmentNewsHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLiveData()

        viewModel.getNews(selectedCountry, selectedCategory, false)

        binding.newsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.newsRecyclerView.adapter = newsRecyclerAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getNews(selectedCountry, selectedCategory, true)
        }

        if(BuildConfig.FLAVOR == "free"){
            val searchEditText = binding.searchView.findViewById<AutoCompleteTextView>(
                androidx.appcompat.R.id.search_src_text
            )
            searchEditText?.isEnabled = false
            searchEditText?.isClickable = false
            searchEditText?.clearFocus()

        } else {
            binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newSearch: String?): Boolean {
                    newSearch?.let { viewModel.searchNews(it, selectedCountry, selectedCategory) }
                    return true
                }
            })
        }

        val countries = resources.getStringArray(R.array.country_list)
        val categories = resources.getStringArray(R.array.category_list)

        SpinnerUtils.setupSpinner(requireContext(), binding.countrySpinner, countries) {
            viewModel.getNews(selectedCountry, selectedCategory, false)
            binding.searchView.clear()
        }

        SpinnerUtils.setupSpinner(requireContext(), binding.filterSpinner, categories) {
            viewModel.getNews(selectedCountry, selectedCategory, false)
            binding.searchView.clear()
        }

    }

    private fun observeLiveData() {
        viewModel.newsState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is NewsUiState.Loading -> {
                    binding.newsProgressBar.show()
                    binding.newsRecyclerView.hide()
                    binding.newsErrorMessage.hide()
                }

                is NewsUiState.Success -> {
                    binding.newsProgressBar.hide()
                    binding.newsRecyclerView.show()
                    binding.newsErrorMessage.hide()
                    newsRecyclerAdapter.updateNews(state.news)
                    binding.swipeRefreshLayout.isRefreshing = false
                }

                is NewsUiState.Error -> {
                    binding.newsProgressBar.hide()
                    binding.newsRecyclerView.hide()
                    binding.newsErrorMessage.show()
                    binding.newsErrorMessage.text = state.message ?: "An error occurred"
                    binding.swipeRefreshLayout.isRefreshing = false
                }
            }
        }
    }

}