package com.example.mytestapp.presentation.screens.main

import androidx.lifecycle.observe
import com.example.mytestapp.R
import com.example.mytestapp.data.model.Hits
import com.example.mytestapp.presentation.screens.PostsViewModel
import com.example.mytestapp.presentation.screens.base.activity.BaseActivity
import com.example.mytestapp.presentation.screens.main.adapter.PostsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_menu_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val viewModel: PostsViewModel by viewModel()
    private val adapter: PostsAdapter = PostsAdapter(::onAdapterItemClicked)

    private fun onAdapterItemClicked(hits: Hits) {
        val selectedItemsCount = adapter.getSelectedItems().size
        val text =
            if (selectedItemsCount == 0) getString(R.string.no_items_selected)
            else getString(R.string.items_selected_format, selectedItemsCount)
        navBarTv.text = text
    }

    override fun initViews() {
        with(recyclerView) {
            adapter = this@MainActivity.adapter
        }
        swipyrefreshlayout.setOnRefreshListener { viewModel.refreshData() }
        viewModel.getPosts().observe(this) {
            swipyrefreshlayout.isRefreshing = false
            adapter.setItems(it)
        }
        viewModel.refreshData()
    }

}