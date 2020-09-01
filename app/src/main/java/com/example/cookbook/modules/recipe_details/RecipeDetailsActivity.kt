package com.example.cookbook.modules.recipe_details

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cookbook.R
import com.example.cookbook.data.models.RecipeBrief
import com.example.cookbook.data.models.RecipeDetails
import kotlinx.android.synthetic.main.activity_recipe_details.*


class RecipeDetailsActivity : AppCompatActivity(), IDetailsRecipeView {
    lateinit var presenter: IDetailsRecipePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)
        configure()
        initViews()

        val recipeId = intent.getStringExtra("RECIPE_ID")
        presenter.onViewAttached(recipeId)
    }

    private fun initViews(){
        recipeDetailsProgressBar.visibility= View.VISIBLE
    }

    private fun configure(){
        presenter = RecipeDetailsPresenter(this)
    }

    override fun showRecipeDetails (data: RecipeDetails) {
        recipeNameTextView.text = data.name
        if (!data.description.isNullOrEmpty()) {recipeDescriptionTextView.text = "   Description: " + data.description}
        else {recipeDescriptionTextView.visibility=View.GONE}

        recipeInstructionTextView.text="   Instructions: " + data.instructions.replace("<br>", "")
        recipeDifficultyTextView.text="Difficulty:"
        ratingBar.visibility=View.VISIBLE
        ratingBar.rating=data.difficulty.toFloat()
        ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            ratingBar.rating=data.difficulty.toFloat()
        }
        viewPager2.adapter = ViewPagerAdapter(data.images)
        if (!data.similar.isNullOrEmpty()) {
            similarRecipesTextView.text="Similar recipes:"

            val myAdapter = ListSimilarRecipesAdapter(data.similar, object : ListSimilarRecipesAdapter.Callback {
                override fun onItemClicked(item: RecipeBrief) {
                    navigateToSimilarRecipeDetails(item.uuid)
                }
            })
            similarRecipesList.adapter = myAdapter
        }

        if(data.uuid!="") {recipeDetailsProgressBar.visibility= View.GONE}

    }

    override fun showWrongFetchToast(message:String) {
        recipeDetailsProgressBar.visibility= View.GONE
        Toast.makeText(this@RecipeDetailsActivity, message, Toast.LENGTH_LONG).show()
    }

    private fun navigateToSimilarRecipeDetails(recipeId: String) {
        val intent=Intent(this@RecipeDetailsActivity, RecipeDetailsActivity::class.java)
        intent.putExtra("RECIPE_ID", recipeId)
        startActivity(intent)
    }
}