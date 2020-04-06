package com.example.mytestapp.presentation.screens.main.adapter

import android.util.SparseBooleanArray
import android.view.View
import android.view.ViewGroup
import androidx.core.util.contains
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mytestapp.R
import com.example.mytestapp.data.model.Hits
import com.example.mytestapp.presentation.screens.base.adapter.RecyclerViewAdapter
import com.example.mytestapp.presentation.screens.utils.getCreatedAtTime
import kotlinx.android.synthetic.main.item_user_rv.view.*
import java.util.*

class PostsAdapter(private val itemClickedAction: (Hits) -> Unit) :
    RecyclerViewAdapter<Hits, PostsAdapter.TaskViewHolder>(DiffUtilItemCallback()) {

    private val selectedItems = SparseBooleanArray()

    fun getSelectedItems(): List<Hits> {
        val items = LinkedList<Hits>()
        for (i in 0 until selectedItems.size()) {
            val key = selectedItems.keyAt(i)
            if (selectedItems.get(key)) {
                items.add(getItem(key))
            }
        }
        return items
    }

    private fun onCheckboxCLicked(position: Int) {
        if (selectedItems.contains(position)) selectedItems.delete(position)
        else selectedItems.put(position, true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
        TaskViewHolder(inflate(parent, R.layout.item_user_rv))

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position), selectedItems.get(position))
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            val action = {
                onCheckboxCLicked(adapterPosition)
                itemClickedAction(getItem(adapterPosition))
                notifyItemChanged(adapterPosition)
            }
            itemView.setOnClickListener { action.invoke() }
            itemView.checkBox.setOnClickListener { action.invoke() }
        }

        fun bind(hits: Hits, isSelected: Boolean) =
            with(itemView) {
                checkBox.isChecked = isSelected
                titleTv.text = context.getString(R.string.title_format, hits.title)
                authorTv.text = context.getString(R.string.author_format, hits.author)
                createdAtTv.text = hits.getCreatedAtTime(context)
            }
    }
}

private class DiffUtilItemCallback : DiffUtil.ItemCallback<Hits>() {

    override fun areItemsTheSame(oldItem: Hits, newItem: Hits) = oldItem.author == newItem.author

    override fun areContentsTheSame(oldItem: Hits, newItem: Hits) = oldItem == newItem

}