package com.example.cookbook.data.models

class Recipe (
    val uuid: String,
    val name: String,
    val images: ArrayList<String>,
    val lastUpdated: Int,
    val description: String,
    val Instructions: String,
    val difficulty: Int
)
