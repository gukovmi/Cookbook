package com.example.cookbook.data.repositories

import android.util.Log
import com.example.cookbook.data.ApiCallback
import com.example.cookbook.data.ApiFactory
import com.example.cookbook.data.models.ListRecipeDetails
import com.example.cookbook.data.models.ListRecipes
import com.example.cookbook.data.models.Recipe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecipesRepository {
    private val recipesService = ApiFactory.recipeService
    fun fetchRecipeList(callback: ApiCallback<ListRecipes>) {
        recipesService.fetchListRecipes()
            .enqueue(object : Callback<ListRecipes> {
                override fun onFailure(call: Call<ListRecipes>, t: Throwable) {
                    callback.onError(message = "Ошибка получения рецептов - ${t.localizedMessage}")
                }
                override fun onResponse(
                    call: Call<ListRecipes>,
                    response: Response<ListRecipes>
                ) {
                    if (response.body()!=null) {
                        callback.onSuccess(data = response.body()!!)
                    }
                    else {
                        callback.onError(message = "response.body()==null")
                    }
                }

            })
    }
    fun fetchByIdRecipe(recipeId: String?, callback: ApiCallback<ListRecipeDetails>) {
        recipesService.fetchRecipeById(recipeId)
            .enqueue(object : Callback<ListRecipeDetails> {
                override fun onFailure(call: Call<ListRecipeDetails>, t: Throwable) {
                    callback.onError(message = "Ошибка получения деталей рецепта - ${t.localizedMessage}")
                }
                override fun onResponse(
                    call: Call<ListRecipeDetails>,
                    response: Response<ListRecipeDetails>
                ) {
                    if (response.body()!=null) {
                        Log.i("uuid", recipeId!!)
                        Log.i("content1", response.body()!!.toString())
                        callback.onSuccess(data = response.body()!!)
                        Log.i("uuid", response.body().toString())
                    }
                    else {
                        callback.onError(message = "response.body()==null")

                    }
                }

            })
    }
}