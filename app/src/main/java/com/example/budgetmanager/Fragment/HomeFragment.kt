package com.example.budgetmanager.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetmanager.Adapters.RoomDataAdapter
import com.example.budgetmanager.DataModel.ExpensesModel
import com.example.budgetmanager.R
import com.example.budgetmanager.UI.ExpensesViewModel
import com.example.budgetmanager.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var expenseViewModel:ExpensesViewModel
    private lateinit var adapter:RoomDataAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        expenseViewModel= ViewModelProvider(requireActivity())[ExpensesViewModel::class.java]
        adapter= RoomDataAdapter(ArrayList())
        setupRecyclerView()

        binding.addFab.setOnClickListener {
            buildDialogBox()
        }
    }

    private fun setupRecyclerView() {
binding.RvRoomData.layoutManager=LinearLayoutManager(requireContext())
        binding.RvRoomData.adapter=adapter
        expenseViewModel.getAllExpenses(requireContext()).observe(requireActivity()) {
            adapter.setData(it as ArrayList<ExpensesModel>)
        }
    }

    var isDialogBoxShowing = true
    private fun buildDialogBox() {
        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_add, null)

        val tv =dialogLayout.findViewById<TextInputLayout>(R.id.textinput_dialog_layout)

        val edtshortdesc=dialogLayout.findViewById<EditText>(R.id.edt_short_desc)
        val arrayAdapter=ArrayAdapter(requireContext(),
            R.layout.auto_complete_type_textview,
            resources.getStringArray(R.array.categories))
        val autocompleteTV = dialogLayout.findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        autocompleteTV.setAdapter(arrayAdapter)




        val edtAmount = dialogLayout.findViewById<EditText>(R.id.edt_amount)

        val builder = MaterialAlertDialogBuilder(requireContext())
            .setView(dialogLayout)
            .setNeutralButton("Cancel") { _, which ->
            }
            .setPositiveButton("Add") { _, which ->
            }
        val dialog = builder.show()
        dialog.getButton(-1).setOnClickListener {
            var cat:String=autocompleteTV.text.toString()
            val temp = edtAmount.text.toString()
            val s1=edtshortdesc.text.toString()
//            Toast.makeText(requireContext(),   "$temp  +  $s1  +  ${cat.length}", Toast.LENGTH_LONG).show()
            if (temp.isEmpty()) {
                Toast.makeText(requireContext(), temp.length.toString(), Toast.LENGTH_LONG).show()

            } else {
                val tempExpenses=
                    ExpensesModel(cat,s1,temp,"26/06/2003")
                expenseViewModel.insertExpense(requireContext(),tempExpenses)
                adapter.notifyDataSetChanged()
                dialog.dismiss()

                isDialogBoxShowing = false
            }
        }
        dialog.getButton(-2).setOnClickListener {

            dialog.dismiss()
            isDialogBoxShowing = false
        }



    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}