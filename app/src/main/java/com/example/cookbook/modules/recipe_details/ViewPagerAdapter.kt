package com.example.cookbook.modules.recipe_details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cookbook.R
import kotlinx.android.synthetic.main.item_photo.view.*


class ViewPagerAdapter(val photos_url:List<String>) : RecyclerView.Adapter<PagerVH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
        PagerVH(LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false))

    override fun getItemCount(): Int = photos_url.size

    override fun onBindViewHolder(holder: PagerVH, position: Int): Unit = holder.itemView.run {
        photoNumber.text = "Photo ${position+1}/${getItemCount()}"
        Glide
            .with(this)
            .load(photos_url[position])
            .into(photoView)
    }
}

class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)