package com.example.budgetmanager.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.budgetmanager.DataModel.ExpensesModel

@Dao
interface ExpensesDao {
    @Insert
    suspend fun insertExpense(exp:ExpensesModel)

    @Delete
    suspend fun deleteExpense (exp:ExpensesModel)

    @Query("SELECT * FROM expensesmodel ORDER BY id DESC")
    fun getallExpenses():LiveData<List<ExpensesModel>>
}