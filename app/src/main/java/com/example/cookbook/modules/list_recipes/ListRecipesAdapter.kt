package com.example.cookbook.modules.list_recipes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cookbook.R
import com.example.cookbook.data.models.Recipe
import kotlinx.android.synthetic.main.activity_recipe_details.*
import kotlinx.android.synthetic.main.item_recipe.view.*
import java.util.*

typealias OnRecipeItemClick = (Recipe) -> Unit

class ListRecipesAdapter(
    private val context: Context,
    var recipes: ArrayList<Recipe>,
    private val onRecipeItemClick: OnRecipeItemClick
) : RecyclerView.Adapter<ListRecipesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindRecipe(recipe: Recipe, onRecipeItemClick: OnRecipeItemClick) {
            itemView.apply {
                itemRecipeNameTextView.text = recipe.name
                if (!recipe.description.isNullOrEmpty()) { itemRecipeDescriptionTextView.text = "   Description: "+ recipe.description}
                else {itemRecipeDescriptionTextView.visibility=View.GONE}
                Glide
                        .with(this)
                    .load(recipe?.images[0])
                    .into(imageView)

                setOnClickListener { onRecipeItemClick(recipe) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false)
        return ViewHolder(view = view)
    }

    override fun getItemCount(): Int {
        return recipes.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindRecipe(recipe = recipes[position], onRecipeItemClick = onRecipeItemClick)
    }
}