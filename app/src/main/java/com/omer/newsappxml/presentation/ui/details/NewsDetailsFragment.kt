package com.omer.newsappxml.presentation.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import coil.load
import com.omer.newsappxml.databinding.FragmentNewsDetailsBinding
import com.omer.newsappxml.domain.model.News
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailsFragment : Fragment() {

    private var _binding : FragmentNewsDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val news = arguments?.getSerializable("news") as? News

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}