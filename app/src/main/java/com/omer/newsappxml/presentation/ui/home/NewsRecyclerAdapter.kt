package com.omer.newsappxml.presentation.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.omer.newsappxml.databinding.NewsRecyclerRowBinding
import com.omer.newsappxml.data.model.News
import coil.load
import com.omer.newsappxml.R

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

    fun updateNews(newNewsList : List<News>) {
        newsList.clear()
        newsList.addAll(newNewsList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.binding.newsTitle.text = newsList[position].title
        holder.binding.newsDescription.text = newsList[position].description

        holder.itemView.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("news", newsList[position])
            }
            Navigation.findNavController(it).navigate(
                R.id.action_newsHomeFragment_to_newsDetailsFragment,
                bundle
            )
        }

        holder.binding.newsImageView.load(newsList[position].urlToImage) {
            crossfade(true)
            placeholder(android.R.drawable.ic_menu_report_image)
            error(android.R.drawable.ic_menu_report_image)
        }
    }
}