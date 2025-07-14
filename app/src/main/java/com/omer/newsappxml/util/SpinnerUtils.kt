package com.omer.newsappxml.util

import android.R
import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

object SpinnerUtils {
    fun setupSpinner(
        context: Context,
        spinner: Spinner,
        items: Array<String>,
        onItemSelected: () -> Unit
    ) {
        val adapter = ArrayAdapter(context, R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                onItemSelected()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
    }