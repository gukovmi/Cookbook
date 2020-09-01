package com.example.cookbook.data

import android.provider.SyncStateContract
import com.example.cookbook.Constants
import com.example.cookbook.data.services.RecipeService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {
    private val myClient : OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(10000, TimeUnit.MILLISECONDS)
        .retryOnConnectionFailure(true)
        .followRedirects(true)
        .build()

    private fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    private fun retrofit() : Retrofit = Retrofit.Builder()
        .client(myClient)
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(provideGson()))
        .build()
    val recipeService: RecipeService= retrofit().create(RecipeService::class.java)
}