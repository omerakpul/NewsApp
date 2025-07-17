package com.omer.newsappxml.presentation.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import coil.load
import com.omer.newsappxml.databinding.FragmentNewsDetailsBinding
import com.omer.newsappxml.databinding.FragmentNewsHomeBinding
import com.omer.newsappxml.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailsFragment : BaseFragment<FragmentNewsDetailsBinding>() {

    private val args: NewsDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentNewsDetailsBinding {
        return FragmentNewsDetailsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val news = args.news

        if (news != null) {
            binding.newsTitle.text = news.title
            binding.newsImageView.load(news.urlToImage)
            binding.newsDescription.text = news.description
        } else {
            binding.newsTitle.text = "News could not found."
        }

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}