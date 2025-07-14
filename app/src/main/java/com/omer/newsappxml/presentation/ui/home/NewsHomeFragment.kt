package com.omer.newsappxml.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.omer.newsappxml.R
import com.omer.newsappxml.databinding.FragmentNewsHomeBinding
import com.omer.newsappxml.presentation.viewmodel.NewsHomeViewModel
import com.omer.newsappxml.util.SpinnerUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsHomeFragment : Fragment() {

    private var _binding: FragmentNewsHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsHomeViewModel by viewModels()
    private val newsRecyclerAdapter = NewsRecyclerAdapter(arrayListOf())

    private val selectedCountry: String
        get() = binding.countrySpinner.selectedItem as? String ?: "us"
    private val selectedCategory: String
        get() = binding.filterSpinner.selectedItem as? String ?: "general"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.news.value.isNullOrEmpty()) {
            viewModel.getNews(selectedCountry,selectedCategory)
        }

        binding.newsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.newsRecyclerView.adapter = newsRecyclerAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.newsRecyclerView.visibility = View.GONE
            binding.newsErrorMessage.visibility = View.GONE
            binding.newsProgressBar.visibility = View.VISIBLE


            viewModel.refreshNews(selectedCountry,selectedCategory)
            viewModel.getNews(selectedCountry,selectedCategory)
            binding.swipeRefreshLayout.isRefreshing = false
        }
        observeLiveData()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }
            override fun onQueryTextChange(newSearch: String?): Boolean {
                newSearch?.let { viewModel.searchNews(it,selectedCountry,selectedCategory) }
                return true
            }
        })

        val countries = resources.getStringArray(R.array.country_list)
        val categories = resources.getStringArray(R.array.category_list)

        SpinnerUtils.setupSpinner(requireContext(), binding.countrySpinner, countries) {
            viewModel.getNews(selectedCountry,selectedCategory)
            binding.searchView.setQuery("", false)
            binding.searchView.clearFocus()
        }

        SpinnerUtils.setupSpinner(requireContext(), binding.filterSpinner, categories) {
            viewModel.getNews(selectedCountry,selectedCategory)
            binding.searchView.setQuery("", false)
            binding.searchView.clearFocus()
        }

    }

    private fun observeLiveData(){
        viewModel.news.observe(viewLifecycleOwner){
            newsRecyclerAdapter.updateNews(it)
            binding.newsRecyclerView.visibility=View.VISIBLE
        }
        viewModel.newsError.observe(viewLifecycleOwner){
            if(it) {
                binding.newsErrorMessage.visibility = View.VISIBLE
                binding.newsRecyclerView.visibility = View.GONE
            } else {
                binding.newsErrorMessage.visibility = View.GONE
            }
        }
        viewModel.newsLoading.observe(viewLifecycleOwner){
            if(it) {
                binding.newsErrorMessage.visibility = View.GONE
                binding.newsRecyclerView.visibility = View.GONE
                binding.newsProgressBar.visibility = View.VISIBLE
            } else {
                binding.newsProgressBar.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}