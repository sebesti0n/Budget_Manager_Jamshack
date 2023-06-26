package com.example.budgetmanager.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.budgetmanager.DataModel.ExpensesModel

@Database(entities = [ExpensesModel::class],
version = 1)
 abstract class ExpensesDatabase:RoomDatabase() {

    abstract fun getExpensesDao() : ExpensesDao


    companion object {

        @Volatile private var instance : ExpensesDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ExpensesDatabase::class.java,
            "Expenses database"
        ).build()

    }
}