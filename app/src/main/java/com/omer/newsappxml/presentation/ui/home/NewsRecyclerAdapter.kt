package com.omer.newsappxml.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.omer.newsappxml.databinding.NewsRecyclerRowBinding
import coil.load
import com.omer.newsappxml.domain.model.News

class NewsRecyclerAdapter(val newsList : ArrayList<News>) : RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder>() {

    class NewsViewHolder(val binding : NewsRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun updateNews(NewsList : List<News>) {
        newsList.clear()
        newsList.addAll(NewsList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.binding.newsTitle.text = newsList[position].title
        holder.binding.newsDescription.text = newsList[position].description

        holder.itemView.setOnClickListener {
            val action = NewsHomeFragmentDirections.actionNewsHomeFragmentToNewsDetailsFragment(newsList[position])
            it.findNavController().navigate(action)
        }

        holder.binding.newsImageView.load(newsList[position].urlToImage) {
            crossfade(true)
            placeholder(android.R.drawable.ic_menu_report_image)
            error(android.R.drawable.ic_menu_report_image)
        }
    }
}