package com.example.mytestapp.presentation.screens.main

import androidx.lifecycle.observe
import com.example.mytestapp.R
import com.example.mytestapp.data.model.Hits
import com.example.mytestapp.presentation.screens.PostsViewModel
import com.example.mytestapp.presentation.screens.base.activity.BaseActivity
import com.example.mytestapp.presentation.screens.main.adapter.PostsAdapter
import com.example.mytestapp.presentation.screens.utils.addItemDecoration
import com.example.mytestapp.presentation.screens.utils.getSelectedItemsCount
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_menu_main.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val viewModel: PostsViewModel by viewModel()
    private val adapter: PostsAdapter = PostsAdapter(::onAdapterItemClicked)

    private fun onAdapterItemClicked(hits: Hits) {
        cl_nav_view.navBarTv.text = getSelectedItemsCount(adapter)
    }

    override fun initViews() {
        with(recyclerView) {
            this.adapter = this@MainActivity.adapter
            this.addItemDecoration(context)
        }
        swipyrefreshlayout.setOnRefreshListener { viewModel.refreshData() }
        viewModel.getPosts().observe(this) {
            swipyrefreshlayout.isRefreshing = false
            adapter.setItems(it)
        }
        viewModel.refreshData()
    }

}