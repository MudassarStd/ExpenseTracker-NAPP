package com.example.expensetrackernapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.expensetrackernapp.R


class RVAdapter(val amounts : List<String>, val accounts : List<String>, val times : List<String>, val categories : List<String>) : Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val view = inflate.inflate(R.layout.rv_row_transaction, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.amount.text = amounts[position]
        holder.categry.text = accounts[position]
        holder.date.text = times[position]
        holder.title.text = categories[position]

        // changing color of account dynamically

        val account = accounts[position]

        val tintList = when(account)
        {
            "cash" -> R.color.green
            "bank" -> R.color.bank
            else -> R.color.yellow
        }

        holder.categry.setBackgroundColor(tintList)



    }

    override fun getItemCount(): Int {
        return amounts.size
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val img = itemView.findViewById<ImageView>(R.id.iv_Logo)
    val title = itemView.findViewById<TextView>(R.id.tvTitle)
    val categry = itemView.findViewById<TextView>(R.id.tvCategory)
    val date = itemView.findViewById<TextView>(R.id.tvDate)
    val amount = itemView.findViewById<TextView>(R.id.tvAmount)
}