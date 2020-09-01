package com.example.cookbook.modules.recipe_details

import com.example.cookbook.data.models.RecipeDetails

interface IDetailsRecipeView {
    fun showRecipeDetails(data: RecipeDetails)
    fun showWrongFetchToast (message: String)
}

interface IDetailsRecipePresenter {
    fun onViewAttached (uuid: String)
}