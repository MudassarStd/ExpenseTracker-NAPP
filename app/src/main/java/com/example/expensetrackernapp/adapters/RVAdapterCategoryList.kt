package com.example.expensetrackernapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.expensetrackernapp.R
import kotlin.math.sign

class RVAdapterCategoryList(val categoryNames : List<String>,  val categoryImages : List<Int>)  : Adapter<CategoryListVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListVH {
        val inflate = LayoutInflater.from(parent.context)
        val view = inflate.inflate(R.layout.sample_layout_category_item, parent, false)
        return CategoryListVH(view)
    }

    override fun onBindViewHolder(holder: CategoryListVH, position: Int) {
        holder.cName.text = categoryNames[position]
        holder.cImage.setImageResource(categoryImages[position])
    }

    override fun getItemCount(): Int {
        return categoryImages.size
    }
}

class CategoryListVH(itemView : View) : RecyclerView.ViewHolder(itemView)
{
    val cName = itemView.findViewById<TextView>(R.id.tvCategoryItem)
    val cImage = itemView.findViewById<ImageView>(R.id.ivCategoryItem)
}