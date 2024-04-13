package com.example.expensetrackernapp.activities
import android.app.DatePickerDialog
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expensetrackernapp.DataModels.DataModel
import com.example.expensetrackernapp.DataModels.TransacDataClass
import com.example.expensetrackernapp.R
import com.example.expensetrackernapp.adapters.RVAdapter
import com.example.expensetrackernapp.adapters.RVAdapterCategoryList
import com.example.expensetrackernapp.adapters.onCategoryClickListenerInterface
import java.text.SimpleDateFormat
import java.util.*
import com.example.expensetrackernapp.databinding.ActivityAddNewTransBinding
import com.google.android.material.textfield.TextInputEditText


class AddNewTrans : AppCompatActivity() , onCategoryClickListenerInterface{
    private lateinit var binding: ActivityAddNewTransBinding

    val categoriesList = listOf<String>("Business","Personal","Health","Education","Travel")
    val transactionMethodsList = listOf<String>("Cash","Bank","PayTM","PayPal","Other")

    private var categoryDialog: AlertDialog? = null
    private lateinit var categorySelected : String

    // getting categories Data
    val categoriesAdapter = RVAdapterCategoryList(DataModel.CategoriesList)




    private lateinit var adapter : RVAdapter


    lateinit var etDate: TextInputEditText
    lateinit var etAccount: TextInputEditText
    lateinit var etAmount: TextInputEditText
    lateinit var etNote: TextInputEditText
    lateinit var etCategory: TextInputEditText
    var categoryImageID : Int = -1

    // ---------------- experiment ----------------------

    var incomeOrExpense = 0


    // getting date and time
    val calendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityAddNewTransBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // =============== experiment ========================


        categoriesAdapter.setOnCategoryClickListenerInterface(this)
        // getting view references in variables
        etDate = binding.etDate
        etAccount = binding.etAccount
        etAmount = binding.etAmount
        etNote = binding.etDate
        etCategory = binding.etCategory

        binding.btnAddTransac.setOnClickListener {

            // collection user Info in variables
            val userAmount = etAmount.text.toString()
            val userTransMethod = etAccount.text.toString()
            val userTime = etDate.text.toString()
            val userCategory = etCategory.text.toString()

            if (userAmount.isNotEmpty() && userTime.isNotEmpty() && userCategory.isNotEmpty() && userTransMethod.isNotEmpty() )
            {
                // directly update variables in  data model

                if (incomeOrExpense == 0) {
                    DataModel.incomeAmount += etAmount.text.toString().toDouble()
                } else if (incomeOrExpense == 1) {
                    DataModel.expenseAmount += etAmount.text.toString().toDouble()
                }
                DataModel.TotalAmount += etAmount.text.toString().toDouble()


                // EXP: Transactions Data Class

                // adding user data to Transactions List<Data Class>
                DataModel.Transactions.add(TransacDataClass(userAmount, userTransMethod, userTime,"note", userCategory,categoryImageID, incomeOrExpense))

            }

            finish()
        }


        etCategory.setOnClickListener {
        showCategoriesDialog()
        }

        etAccount.setOnClickListener {
        showtransactionMethodsDialog()
        }

        // ======================== Setting button background colors on selecting them ========================
        binding.btnIncome.setOnClickListener {
            binding.btnIncome.backgroundTintList = getColorStateList(R.color.green)
            binding.btnExpense.backgroundTintList = getColorStateList(R.color.colorPrimaryLight)

            // to track which is being entered (income/expense)
            incomeOrExpense = 0
        }

        binding.btnExpense.setOnClickListener {
            binding.btnExpense.backgroundTintList = getColorStateList(R.color.red)
            binding.btnIncome.backgroundTintList = getColorStateList(R.color.colorPrimaryLight)

            // to track which is being entered (income/expense)
            incomeOrExpense = 1
        }

        // -------------------------------------------------------------------------------------------------------

        etDate.setOnClickListener{
            showDatePickerDialog()
        }

    }


    private fun showCategoriesDialog() {


        val dialogView = layoutInflater.inflate(R.layout.dialog_categories_rv_layout, null)

        val rvCategories : RecyclerView = dialogView.findViewById(R.id.rvCategoriesDialog)

        rvCategories.adapter = categoriesAdapter
        rvCategories.layoutManager = GridLayoutManager(this,3)

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select a Category")
            .setView(dialogView)

            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
        categoryDialog = builder.create()
        categoryDialog?.show()
    }

    private fun showtransactionMethodsDialog() {

        val categories = transactionMethodsList.toTypedArray()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select Transaction Method")
            .setItems(categories) { _, which ->
                val selectedCategory = transactionMethodsList[which]
                etAccount.setText(selectedCategory)
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
                val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                etDate.setText(sdf.format(calendar.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePicker.show()
    }

    override fun onCategoryItemClick(itemPosition: Int) {
        categorySelected = DataModel.CategoriesList[itemPosition].category
        categoryImageID = DataModel.CategoriesList[itemPosition].categoryIcon
        etCategory.setText(categorySelected)
        categoryDialog?.dismiss()
    }

}