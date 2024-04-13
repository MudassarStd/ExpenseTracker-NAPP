package com.example.expensetrackernapp.DataModels

import com.example.expensetrackernapp.R

class DataModel{

    companion object{


        var incomeAmount : Double = 0.00
        var expenseAmount : Double = 0.00
        var TotalAmount :   Double = 0.00


        // Exp: Transactions Data class

        val Transactions : MutableList<TransacDataClass> = mutableListOf()

        val CategoriesList : MutableList<CategoryData> = mutableListOf(CategoryData("Business", R.drawable.ic_cash),
            CategoryData("Personal", R.drawable.ic_bank), CategoryData("Education", R.drawable.ic_paytm),
            CategoryData("Travel", R.drawable.ic_card), CategoryData("Other", R.drawable.ic_other))
    }
}

