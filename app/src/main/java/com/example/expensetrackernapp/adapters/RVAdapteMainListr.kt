package com.example.expensetrackernapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.expensetrackernapp.DataModels.CategoryData
import com.example.expensetrackernapp.DataModels.TransacDataClass
import com.example.expensetrackernapp.R

interface onTransactionItemClickInterface{
    fun onItemClick(itemPosition : Int)
}

class RVAdapter(val Trans : List<TransacDataClass>,
                var currentDate : String) : Adapter<ViewHolder>() {

    private lateinit var TransInterfaceListener : onTransactionItemClickInterface
    fun setonTransactionItemClickInterface(listener : onTransactionItemClickInterface)
    {
        TransInterfaceListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val view = inflate.inflate(R.layout.rv_row_transaction, parent, false)
        return ViewHolder(view, TransInterfaceListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(currentDate == Trans[position].time) {

//            holder.tvNoTransac.visibility = View.GONE
            holder.itemView.visibility = View.VISIBLE // Ensure the item is visible

            holder.amount.text = Trans[position].amount
            holder.transMethod.text = Trans[position].transacMethod
            holder.date.text = Trans[position].time
            holder.category.text = Trans[position].category
            holder.categoryIcon.setImageResource(Trans[position].categoryIcon)

            // dynamic colors of category icon
            val categoryIconColor = when(Trans[position].category){
                "Business" -> R.color.business
                "Personal" -> R.color.personal
                "Education" -> R.color.education
                "Travel" -> R.color.travel
                else -> R.color.colorPrimary
            }

            holder.categoryIcon.backgroundTintList = ContextCompat.getColorStateList(holder.itemView.context, categoryIconColor)



            if (Trans[position].incomeOrExpense == 0)
            {
                holder.amount.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.green));
            }
            else if(Trans[position].incomeOrExpense == 1)
            {
                holder.amount.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.red))
            }


            // changing color of TransactionMethod dynamically
            val account = Trans[position].transacMethod
            val tintList = when(account) {
                "Cash" -> R.color.green
                "Bank" -> R.color.bank
                "PayTM" -> R.color.yellow
                else -> R.color.red
            }


            holder.transMethod.backgroundTintList = ContextCompat.getColorStateList(holder.itemView.context, tintList)

        }

        else {

            // In case currentDate doesn't match times[position], RecyclerView handles the visibility
            holder.itemView.visibility = View.GONE
//            holder.tvNoTransac.visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return Trans.size
    }


    // updating current date with new date from home Transaction frag
    fun updateCurrentDate(newDate : String)
    {
        currentDate = newDate
    }

}

class ViewHolder(itemView: View, listener: onTransactionItemClickInterface) : RecyclerView.ViewHolder(itemView)
{
    val categoryIcon = itemView.findViewById<ImageView>(R.id.iv_Logo)
    val category = itemView.findViewById<TextView>(R.id.tvCategory)
    val transMethod = itemView.findViewById<TextView>(R.id.tvTransMethod)
    val date = itemView.findViewById<TextView>(R.id.tvDate)
    val amount = itemView.findViewById<TextView>(R.id.tvAmount)
    val tvNoTransac = itemView.findViewById<TextView>(R.id.tvNoTransactionsYet)


    // handling item clicks

    init {
        itemView.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }
}