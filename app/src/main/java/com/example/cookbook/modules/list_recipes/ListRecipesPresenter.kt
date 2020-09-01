package com.example.cookbook.modules.list_recipes

import android.content.Intent
import android.util.Log
import com.example.cookbook.data.ApiCallback
import com.example.cookbook.data.models.Recipe
import com.example.cookbook.data.models.ListRecipes
import com.example.cookbook.data.repositories.RecipesRepository

class ListRecipesPresenter(val view: IListRecipesView) : IListRecipesPresenter {

    private val recipesRepository = RecipesRepository()

    override fun onViewAttached() {
        getRecipes()
    }

    private fun getRecipes() {
        recipesRepository.fetchRecipeList(object : ApiCallback<ListRecipes> {
            override fun onSuccess(data: ListRecipes) {
                view.showRecipes(recipes = data.recipes)
            }

            override fun onError(message: String) {
                Log.e("Fetch recipes Error", message)
            }
        })
    }

    override fun onRecipeItemClick(recipe: Recipe) {
        view.navigateToRecipeDetails(recipeId = recipe.uuid)
    }
}