package com.example.expensetrackernapp.activities

import android.app.DatePickerDialog
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import com.example.expensetrackernapp.R
import java.text.SimpleDateFormat
import java.util.*
import com.example.expensetrackernapp.databinding.ActivityAddNewTransBinding
import com.google.android.material.textfield.TextInputEditText

class AddNewTrans : AppCompatActivity() {
    private lateinit var binding: ActivityAddNewTransBinding

    val categoriesList = listOf<String>("Business","Personal","Health","Education","Travel")

    lateinit var etDate: TextInputEditText
    val calendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityAddNewTransBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.etCategory.setOnClickListener {
        showCategoriesDialog()
        }

        // ======================== Setting button background colors on selecting them ========================
        binding.btnIncome.setOnClickListener {
            binding.btnIncome.backgroundTintList = getColorStateList(R.color.green)
            binding.btnExpense.backgroundTintList = getColorStateList(R.color.yellow)
        }

        binding.btnExpense.setOnClickListener {
            binding.btnExpense.backgroundTintList = getColorStateList(R.color.red)
            binding.btnIncome.backgroundTintList = getColorStateList(R.color.yellow)
        }

        // -------------------------------------------------------------------------------------------------------

        binding.etDate.setOnClickListener{
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

        // Set a minimum date if needed
        // datePicker.datePicker.minDate = System.currentTimeMillis() - 1000

        datePicker.show()
    }
}