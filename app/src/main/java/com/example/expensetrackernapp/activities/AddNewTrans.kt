package com.example.expensetrackernapp.activities
import android.app.DatePickerDialog
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetrackernapp.DataModels.DataModel
import com.example.expensetrackernapp.R
import com.example.expensetrackernapp.adapters.RVAdapter
import java.text.SimpleDateFormat
import java.util.*
import com.example.expensetrackernapp.databinding.ActivityAddNewTransBinding
import com.google.android.material.textfield.TextInputEditText


class AddNewTrans : AppCompatActivity() {
    private lateinit var binding: ActivityAddNewTransBinding

    val categoriesList = listOf<String>("Business","Personal","Health","Education","Travel")

    // =============== experiment ========================

    // lists from data Model

    val amountList = DataModel.amountList
    val accountList = DataModel.accountList
    val timeList = DataModel.timeList
    val noteList = DataModel.noteList
    val categoryList = DataModel.categoryList



//    private lateinit var adapter : RecyclerView

    private lateinit var adapter : RVAdapter


    lateinit var etDate: TextInputEditText
    lateinit var etAccount: TextInputEditText
    lateinit var etAmount: TextInputEditText
    lateinit var etNote: TextInputEditText
    lateinit var etCategory: TextInputEditText

    // ---------------- experiment ----------------------

    var incomeOrExpense = 0


    val calendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityAddNewTransBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // =============== experiment ========================


        etDate = binding.etDate
        etAccount = binding.etAccount
        etAmount = binding.etAmount
        etNote = binding.etDate
        etCategory = binding.etCategory

        // ---------------- experiment ----------------------


        // collecting data from et's

        binding.btnAddTransac.setOnClickListener {
            if (etAccount.text.toString().isNotEmpty() && etAmount.text.toString().isNotEmpty() && etDate.text.toString().isNotEmpty() && etNote.text.toString().isNotEmpty())
            {

                // directly update variables in  data model

                if (incomeOrExpense == 0) {
                    DataModel.incomeAmount += etAmount.text.toString().toDouble()
                } else if (incomeOrExpense == 1) {
                    DataModel.expenseAmount += etAmount.text.toString().toDouble()
                }
                DataModel.TotalAmount += etAmount.text.toString().toDouble()

                amountList.add((etAmount.text.toString()))
                accountList.add((etAccount.text.toString()))
//                noteList.add((etNote.text.toString()))
                timeList.add((etDate.text.toString()))
                categoryList.add(etCategory.text.toString())


            }

            finish()
        }


        binding.etCategory.setOnClickListener {
        showCategoriesDialog()
        }

        // ======================== Setting button background colors on selecting them ========================
        binding.btnIncome.setOnClickListener {
            binding.btnIncome.backgroundTintList = getColorStateList(R.color.green)
            binding.btnExpense.backgroundTintList = getColorStateList(R.color.yellow)

            // to track which is being entered (income/expense)
            incomeOrExpense = 0
        }

        binding.btnExpense.setOnClickListener {
            binding.btnExpense.backgroundTintList = getColorStateList(R.color.red)
            binding.btnIncome.backgroundTintList = getColorStateList(R.color.yellow)

            // to track which is being entered (income/expense)
            incomeOrExpense = 1
        }

        // -------------------------------------------------------------------------------------------------------

        etDate.setOnClickListener{
            showDatePickerDialog()
        }

    }

    private fun showCategoriesDialog() {
        val categories = categoriesList.toTypedArray()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select a Category")
            .setItems(categories) { _, which ->
                val selectedCategory = categoriesList[which]
                binding.etCategory.setText(selectedCategory)
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
       .create()
        builder.show()
    }



    private fun showDatePickerDialog() {
        val datePicker = DatePickerDialog(
            this,
            { _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                // Update calendar with chosen date
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                // Format the chosen date and set it to the TextInputEditText
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                etDate.setText(sdf.format(calendar.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )



        datePicker.show()
    }
}