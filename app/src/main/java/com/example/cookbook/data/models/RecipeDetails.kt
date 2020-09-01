package com.example.cookbook.data.models

class RecipeDetails
    (val uuid: String,
    val name: String,
    val images: List<String>,
    val lastUpdated: Long,
    val description: String,
    val instructions: String,
    val difficulty: Long,
    val similar: List<RecipeBrief>)
