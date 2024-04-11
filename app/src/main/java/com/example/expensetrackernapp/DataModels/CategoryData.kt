package com.example.expensetrackernapp.DataModels

import androidx.recyclerview.widget.RecyclerView

class DataModel{

    companion object{


        var incomeAmount : Double = 0.00
        var expenseAmount : Double = 0.00
        var TotalAmount :   Double = 0.00





        val amountList : MutableList<String> =  mutableListOf()
        val accountList : MutableList<String> =  mutableListOf()
        val timeList : MutableList<String> =  mutableListOf()
        val noteList : MutableList<String> =  mutableListOf()
        val categoryList : MutableList<String> =  mutableListOf()


        fun addData(amount : String, account : String, time : String, category : String)
        {
            amountList.add(amount)
            accountList.add(account)
            timeList.add(time)
            categoryList.add(category)
        }

    }
}


//package com.example.expensetrackernapp.DataModels
//
//private class CategoryData {
//
//    var categoryName : String? = null
//    var categoryImage :  Int = -1
//
//    // secondary constructor
//    constructor(categoryName: String?, categoryImage: Int) {
//        this.categoryName = categoryName
//        this.categoryImage = categoryImage
//    }
//
//
//    // ============================= defining getters and setters for data =============================
//
//
//    // category name getter
//    fun getCategoryName() : String?
//    {
//        return categoryName
//    }
//
//    // category name setter
//    fun setCategoryName(categoryName : String?)
//    {
//        this.categoryName = categoryName
//    }
//
//    // category name setter
//    fun getCategoryImage() : Int
//    {
//        return categoryImage
//    }
//    // category name setter
//    fun setCategoryImage(categoryImage : Int)
//    {
//        this.categoryImage = categoryImage
////    }