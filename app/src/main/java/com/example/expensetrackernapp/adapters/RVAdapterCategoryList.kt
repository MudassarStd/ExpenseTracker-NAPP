package com.example.expensetrackernapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.expensetrackernapp.DataModels.CategoryData
import com.example.expensetrackernapp.R
import kotlin.math.sign


interface onCategoryClickListenerInterface{
    fun onCategoryItemClick(itemPosition : Int)
}


class RVAdapterCategoryList(val categoriesClass : List<CategoryData>)  : Adapter<CategoryListVH>() {

    private lateinit var interfaceListener : onCategoryClickListenerInterface
    fun setOnCategoryClickListenerInterface(listener : onCategoryClickListenerInterface)
    {
        interfaceListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryListVH {
        val inflate = LayoutInflater.from(parent.context)
        val view = inflate.inflate(R.layout.sample_layout_category_item, parent, false)
        return CategoryListVH(view, interfaceListener)
    }

    override fun onBindViewHolder(holder: CategoryListVH, position: Int) {
        holder.cName.text = categoriesClass[position].category
        holder.cImage.setImageResource(categoriesClass[position].categoryIcon)
    }

    override fun getItemCount(): Int {
        return categoriesClass.size
    }
}

class CategoryListVH(itemView : View, listener : onCategoryClickListenerInterface) : RecyclerView.ViewHolder(itemView)
{
    val cName = itemView.findViewById<TextView>(R.id.tvCategoryItem)
    val cImage = itemView.findViewById<ImageView>(R.id.ivCategoryItem)


    init {
        itemView.setOnClickListener {
            listener.onCategoryItemClick(adapterPosition)
        }
    }
}