package com.example.expensetrackernapp.fragments

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensetrackernapp.DataModels.CategoryData
import com.example.expensetrackernapp.DataModels.DataModel
import com.example.expensetrackernapp.R
import com.example.expensetrackernapp.activities.AddNewTrans
import com.example.expensetrackernapp.adapters.RVAdapter
import com.example.expensetrackernapp.adapters.onTransactionItemClickInterface
import com.example.expensetrackernapp.databinding.FragmentHomeTransBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HomeTransFragment : Fragment() , onTransactionItemClickInterface {
    private var _binding: FragmentHomeTransBinding? = null
    private val binding get() = _binding!!

    // creating instance of Calendar to avoid creation of new instance of it each time
    var calendar = Calendar.getInstance()

    val listOfOptions = mutableListOf("Edit", "Delete", "View Note")


    // instantiating RVAdapterMainTransactions
    val adapter = RVAdapter(DataModel.Transactions, getCurrentDate(0))

    private lateinit var tvIncome : TextView
    private lateinit var tvExpense : TextView
    private lateinit var tvTotal : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeTransBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // invoking interface
        adapter.setonTransactionItemClickInterface(this)

        tvIncome = binding.tvHomeTransIncome
        tvExpense = binding.tvHomeTransExpense
        tvTotal = binding.tvHomeTransTotal

        val currentDate = binding.tvCurrentDate
        val ivNextDate = binding.ivNextdate
        val ivPrevDate = binding.ivPreviousdate

        // setting current date
        currentDate.text = getCurrentDate(0)


        ivNextDate.setOnClickListener {
            // get current date and increment it by 1
            val newDate = getCurrentDate(1)
            currentDate.text = newDate
            adapter.updateCurrentDate(newDate)
            adapter.notifyDataSetChanged()

        }

        ivPrevDate.setOnClickListener {
            // Decrement the current date by 1
            val newDate = getCurrentDate(-1)
            currentDate.text = newDate
            adapter.updateCurrentDate(newDate)
            adapter.notifyDataSetChanged()
        }

        // creating object of our RV
        val rv = binding.rvHomeTransEntries

        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(requireContext())


        binding.fabtnAddNewTrasac.setOnClickListener {
           startActivity(Intent(requireActivity(), AddNewTrans::class.java))
       }
    }

    private fun showActionsDialog() {

        val dialogView = layoutInflater.inflate(R.layout.action_dialog_layout, null)

        val categories = listOfOptions.toTypedArray()
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Actions")
            .setItems(categories) { _, which ->
                val selectedActions = listOfOptions[which]
                onActionSelected(selectedActions)
            }
            .setNegativeButton("Close") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
        builder.show()
    }

    private fun onActionSelected(action : String)
    {
        if (action == "Edit")
        {
            startActivity(Intent(requireContext(), AddNewTrans::class.java))
        }
    }
    override fun onResume() {
        super.onResume()
        adapter.notifyItemChanged(-1)

        tvTotal.text = DataModel.TotalAmount.toString()
        tvExpense.text = DataModel.expenseAmount.toString()
        tvIncome.text = DataModel.incomeAmount.toString()
    }

    // offset is the value by which to increment No. of days
    fun getCurrentDate(offset: Int): String {
        calendar.add(Calendar.DAY_OF_YEAR, offset)
        val dateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(itemPosition: Int) {
        Toast.makeText(requireContext(), "this", Toast.LENGTH_SHORT).show()
        showActionsDialog()
    }
}
