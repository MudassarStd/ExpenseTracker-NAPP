package com.example.expensetrackernapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.expensetrackernapp.R

class RVAdapter() : Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val view = inflate.inflate(R.layout.rv_row_transaction, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val img = itemView.findViewById<ImageView>(R.id.iv_Logo)
    val title = itemView.findViewById<TextView>(R.id.tvTitle)
    val categry = itemView.findViewById<TextView>(R.id.tvCategory)
    val date = itemView.findViewById<TextView>(R.id.tvDate)
    val amount = itemView.findViewById<TextView>(R.id.tvAmount)
}