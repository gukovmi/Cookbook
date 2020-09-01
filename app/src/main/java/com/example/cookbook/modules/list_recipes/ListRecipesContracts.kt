package com.example.cookbook.modules.list_recipes

import com.example.cookbook.data.models.Recipe
import java.util.*

interface IListRecipesView {
    fun showRecipes(recipes: ArrayList<Recipe>)
    fun navigateToRecipeDetails(recipeId: String)
}

interface IListRecipesPresenter {
    fun onViewAttached()
    fun onRecipeItemClick(recipe: Recipe)
}