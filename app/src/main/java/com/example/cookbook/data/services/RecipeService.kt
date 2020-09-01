package com.example.cookbook.data.services

import com.example.cookbook.data.models.ListRecipeDetails
import com.example.cookbook.data.models.ListRecipes
import com.example.cookbook.data.models.Recipe
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RecipeService{
    @GET("/recipes")
    fun fetchListRecipes(): Call<ListRecipes>

    @GET("/recipes/{uuid}")
    fun fetchRecipeById(@Path("uuid") recipeId: String?) : Call<ListRecipeDetails>

}
