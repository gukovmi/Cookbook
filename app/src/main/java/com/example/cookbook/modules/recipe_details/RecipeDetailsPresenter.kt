package com.example.cookbook.modules.recipe_details

import android.util.Log
import com.example.cookbook.data.ApiCallback
import com.example.cookbook.data.models.ListRecipeDetails
import com.example.cookbook.data.repositories.RecipesRepository

class RecipeDetailsPresenter (val view: IDetailsRecipeView): IDetailsRecipePresenter {

    val recipesRepository = RecipesRepository()
    override fun onViewAttached(uuid: String) {
        getRecipeDetails(uuid)
    }

    private fun getRecipeDetails(recipeId: String) {
        recipesRepository.fetchByIdRecipe(recipeId, object : ApiCallback<ListRecipeDetails> {
            override fun onSuccess(data: ListRecipeDetails) {
                view.showRecipeDetails(data.recipe)
            }

            override fun onError(message: String) {
                Log.e("Fetch recipe error", "$message")
                view.showWrongFetchToast(message)
            }
        })
    }
}