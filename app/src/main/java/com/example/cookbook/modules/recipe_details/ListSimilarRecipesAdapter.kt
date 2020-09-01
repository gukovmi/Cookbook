package com.example.cookbook.modules.recipe_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cookbook.R
import com.example.cookbook.data.models.RecipeBrief

class ListSimilarRecipesAdapter (var items: List<RecipeBrief>, val callback: Callback) : RecyclerView.Adapter<ListSimilarRecipesAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_recipe_similar, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class MainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val similarTitle=itemView.findViewById<TextView>(R.id.similarRecipeTitleTextView)
        fun bind(item: RecipeBrief) {
            similarTitle.text = item.name
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) callback.onItemClicked(items[adapterPosition])
            }
        }
    }

    interface Callback {
        fun onItemClicked(item: RecipeBrief)
    }
}