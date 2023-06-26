package com.example.budgetmanager.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.budgetmanager.DataModel.ExpensesModel
import com.example.budgetmanager.Database.ExpensesDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ExpensesRepository {
    companion object{
        var expensesDatabase:ExpensesDatabase?=null

        private fun intialiseDB(context: Context): ExpensesDatabase?
        {
            return ExpensesDatabase.invoke(context)
        }

        fun insertExpense(context: Context,exp:ExpensesModel)
        {
            expensesDatabase= intialiseDB(context)

            CoroutineScope(IO).launch {
                expensesDatabase!!.getExpensesDao().insertExpense(exp)
            }
        }
        fun deleteExpense(context: Context,exp:ExpensesModel)
        {
            expensesDatabase= intialiseDB(context)

            CoroutineScope(IO).launch {
                expensesDatabase!!.getExpensesDao().deleteExpense(exp)
            }
        }

        fun getAllExpenses(context: Context): LiveData<List<ExpensesModel>>
        {
               expensesDatabase= intialiseDB(context)
               return expensesDatabase!!.getExpensesDao().getallExpenses()

        }
    }
}