package com.example.budgetmanager.UI

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.budgetmanager.DataModel.ExpensesModel
import com.example.budgetmanager.Repository.ExpensesRepository


class ExpensesViewModel: ViewModel() {
    fun insertExpense(context: Context, exp: ExpensesModel)
    {
        ExpensesRepository.insertExpense(context,exp)

    }
    fun deleteExpense(context: Context, exp: ExpensesModel)
    {
        ExpensesRepository.deleteExpense(context, exp)
    }

    fun getAllExpenses(context: Context): LiveData<List<ExpensesModel>>
    {
       return ExpensesRepository.getAllExpenses(context)
    }
}