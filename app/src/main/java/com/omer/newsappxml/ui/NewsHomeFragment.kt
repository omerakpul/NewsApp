package com.omer.newsappxml.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.omer.newsappxml.R
import com.omer.newsappxml.adapter.NewsRecyclerAdapter
import com.omer.newsappxml.databinding.FragmentNewsHomeBinding
import com.omer.newsappxml.viewmodel.NewsHomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsHomeFragment : Fragment() {

    private var _binding: FragmentNewsHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: NewsHomeViewModel by viewModels()
    private val newsRecyclerAdapter = NewsRecyclerAdapter(arrayListOf())

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

        val defaultCountry = "us"
        val defaultCategory = "general"
        viewModel.takeDataFromRoom(defaultCountry, defaultCategory)

        binding.newsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.newsRecyclerView.adapter = newsRecyclerAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.newsRecyclerView.visibility = View.GONE
            binding.newsErrorMessage.visibility = View.GONE
            binding.newsProgressBar.visibility = View.VISIBLE


            val selectedCountry = binding.countrySpinner.selectedItem as String
            val selectedCategory = binding.filterSpinner.selectedItem as String

            viewModel.takeDataFromInternet(selectedCountry, selectedCategory)
            binding.swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchNews(it) }
                return true
            }
            override fun onQueryTextChange(newSearch: String?): Boolean {
                newSearch?.let { viewModel.searchNews(it) }
                return true
            }
        })

        val countries = resources.getStringArray(R.array.country_list)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, countries)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        binding.countrySpinner.adapter = adapter

        binding.countrySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCountry = countries[position]
                val selectedCategory = binding.filterSpinner.selectedItem as String

                viewModel.getNewsRoomOrInternet(selectedCountry,selectedCategory)
                binding.searchView.setQuery("", false)
                binding.searchView.clearFocus()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) { }
        }

        val categories = resources.getStringArray(R.array.category_list)
        val adapterFilter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        binding.filterSpinner.adapter = adapterFilter

        binding.filterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                val selectedCountry = binding.countrySpinner.selectedItem as String
                val selectedCategory = categories[position]

                viewModel.getNewsRoomOrInternet(selectedCountry,selectedCategory)
                binding.searchView.setQuery("", false)
                binding.searchView.clearFocus()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) { }
        }
    }

    private fun observeLiveData(){
        viewModel.news.observe(viewLifecycleOwner){
            newsRecyclerAdapter.updateNews(it)
            binding.newsRecyclerView.visibility=View.VISIBLE
        }
        viewModel.newsErrorMessage.observe(viewLifecycleOwner){
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