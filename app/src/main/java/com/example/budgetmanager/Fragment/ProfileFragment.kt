package com.example.budgetmanager.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.example.budgetmanager.DataModel.ExpensesModel
import com.example.budgetmanager.Helper.addHelper
import com.example.budgetmanager.R
import com.example.budgetmanager.UI.ExpensesViewModel
import com.example.budgetmanager.databinding.FragmentProfileBinding
import kotlin.properties.Delegates

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var expenseViewModel: ExpensesViewModel
   var costTransportation =0
    var costHealth = 0
    var costFood = 0
    var costOthers =0
    var totalCost = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        expenseViewModel= ViewModelProvider(requireActivity())[ExpensesViewModel::class.java]

        setupChartView(
        )

    }

    private fun setupChartView() {
        expenseViewModel.getAllExpenses(requireContext()).observe(requireActivity()) {
            val helper=addHelper(it as ArrayList<ExpensesModel>)
            val temp = helper.getAll()
            costFood=temp[2]
            costHealth=temp[1]
            costTransportation=temp[0]
            costOthers=temp[3]
            totalCost=temp[4]
        }

        val pie=AnyChart.pie()
        val t1=costFood
        val t2=costHealth
        val t3=costTransportation
        val t4=costOthers
        Toast.makeText(requireContext(),"$t1 + $t2 + $t3",Toast.LENGTH_LONG).show()

       val dataEntries:List<DataEntry> =  listOf(
           ValueDataEntry("Transportation",t3),
           ValueDataEntry("Health",t2),
           ValueDataEntry("Food",t1),
           ValueDataEntry("Others",t4)
       )
        pie.data(dataEntries)
        pie.title("Expenses")
        binding.pieChart.setChart(pie)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}