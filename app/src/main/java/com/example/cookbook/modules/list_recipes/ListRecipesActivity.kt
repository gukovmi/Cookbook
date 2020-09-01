package com.example.cookbook.modules.list_recipes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.cookbook.R
import kotlinx.android.synthetic.main.activity_list_recipes.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cookbook.modules.recipe_details.RecipeDetailsActivity
import com.example.cookbook.data.models.Recipe

class ListRecipesActivity : AppCompatActivity(), IListRecipesView {

    lateinit var presenter: IListRecipesPresenter
    private var listRecipesAdapter: ListRecipesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_recipes)
        configure()
        initViews()
        presenter.onViewAttached()
    }

    private fun initViews() {
        listRecipesProgressBar.visibility = View.VISIBLE
        listRecipesRecyclerView.visibility = View.GONE

        listRecipesRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun configure() {
        presenter = ListRecipesPresenter(this)
    }

    override fun showRecipes(recipes: ArrayList<Recipe>) {

        if (listRecipesAdapter != null) {
            listRecipesAdapter?.recipes = recipes
            listRecipesAdapter?.notifyDataSetChanged()
        } else {
            listRecipesAdapter = ListRecipesAdapter(
                context = this,
                recipes = recipes,
                onRecipeItemClick = { recipe -> presenter.onRecipeItemClick(recipe = recipe) })

            listRecipesRecyclerView.adapter = listRecipesAdapter
        }
        listRecipesProgressBar.visibility = View.GONE
        listRecipesRecyclerView.visibility = View.VISIBLE
    }

    override fun navigateToRecipeDetails(recipeId: String) {
        val intent = Intent(this, RecipeDetailsActivity::class.java)
        intent.putExtra("RECIPE_ID", recipeId)
        startActivity(intent)

    }
}
