package com.example.expensetrackernapp

import com.example.expensetrackernapp.DataModels.CategoryData


data class DataClass (
    val amount : String,
    val transacMethod : String,
    val time : String,
    val note : String,
    val category : String,
    val categoryIcon : Int,
    val incomeOrExpense : Int
)

    fun main()
    {

        val originalCategoriesList : MutableList<DataClass> = mutableListOf(
            DataClass("1200", "Cash", "14 April 2024", "Note", "Business",1000, 0),
            DataClass("1200", "Bank", "15 April 2024", "Note", "Personal",1000, 0),
            DataClass("1200", "PayTM", "15 April 2024", "Note", "Other",1000, 0),
            DataClass("1200", "Cash", "14 April 2024", "Note", "Business",1000, 0),
            DataClass("1200", "Cash", "16 April 2024", "Note", "Education",1000, 0),
            DataClass("1200", "Bank", "14 April 2024", "Note", "Travel",1000, 0),
            DataClass("1200", "Other", "16 April 2024", "Note", "Education",1000, 0),
        )

        val filteredCategories : MutableList<DataClass> = mutableListOf()

        val mapTransactions = mutableMapOf<String,MutableList<DataClass>>()


        for (cate in originalCategoriesList)
        {
            if(cate.time == "14 April 2024")
            {
                filteredCategories.add(DataClass(cate.amount, cate.transacMethod, cate.time, cate.note,cate.category, cate.categoryIcon, cate.incomeOrExpense))
                mapTransactions["14 april 2024"] = filteredCategories
            }


        }

//       print(CategoriesList[0].category)
//
        println()
        println()
        println()
        println()
        println(mapTransactions["14 april 2024"])
    }

