package com.omer.newsappxml.util

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.omer.newsappxml.R

object SpinnerUtils {
    fun setupSpinner(
        context: Context,
        spinner: Spinner,
        items: Array<String>,
        onItemSelected: () -> Unit
    ) {
        val adapter = ArrayAdapter(context, R.layout.spinner_item, items)
        adapter.setDropDownViewResource(R.layout.spinner_item_dropdown)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                onItemSelected()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
    }