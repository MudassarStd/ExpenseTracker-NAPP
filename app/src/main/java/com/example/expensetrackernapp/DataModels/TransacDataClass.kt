package com.example.expensetrackernapp.DataModels

 data class TransacDataClass (
     val amount : String,
     val transacMethod : String,
     val time : String,
     val note : String,
     val category : String,
     val categoryIcon : Int,
     val incomeOrExpense : Int
 )


data class CategoryData(
    val category : String,
    val categoryIcon : Int
)