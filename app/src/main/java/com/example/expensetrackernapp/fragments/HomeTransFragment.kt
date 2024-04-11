package com.example.expensetrackernapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensetrackernapp.DataModels.DataModel
import com.example.expensetrackernapp.activities.AddNewTrans
import com.example.expensetrackernapp.adapters.RVAdapter
import com.example.expensetrackernapp.databinding.FragmentHomeTransBinding

class HomeTransFragment : Fragment() {
    private var _binding: FragmentHomeTransBinding? = null
    private val binding get() = _binding!!

    val adapter = RVAdapter(DataModel.amountList, DataModel.accountList, DataModel.timeList, DataModel.categoryList)

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


        tvIncome = binding.tvHomeTransIncome
        tvExpense = binding.tvHomeTransExpense
        tvTotal = binding.tvHomeTransTotal

        val rv = binding.rvHomeTransEntries


        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(requireContext())



       binding.fabtnAddNewTrasac.setOnClickListener {
           startActivity(Intent(requireActivity(), AddNewTrans::class.java))
       }




    }


    override fun onResume() {
        super.onResume()
        adapter.notifyItemChanged(-1)

        tvTotal.text = DataModel.TotalAmount.toString()
        tvExpense.text = DataModel.expenseAmount.toString()
        tvIncome.text = DataModel.incomeAmount.toString()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
