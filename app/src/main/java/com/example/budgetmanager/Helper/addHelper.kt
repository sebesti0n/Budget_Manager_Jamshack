package com.example.budgetmanager.Helper

import android.util.Log
import com.example.budgetmanager.DataModel.ExpensesModel

class addHelper(val list:ArrayList<ExpensesModel>) {
    var totalCost = 0

    fun getAll():ArrayList<Int>{
        var temp:ArrayList<Int> = ArrayList()
        temp.add(0);
        temp.add(0);
        temp.add(0);
        temp.add(0);
        temp.add(0);
        for(i in list){
//            Log.d("SEBA","$totalCost")
            temp[4]+=i.amount.toInt()
            if(i.category.equals("Transportation"))temp[0]+=i.amount.toInt()
            else if(i.category=="Health")temp[1]+=i.amount.toInt()
            else if(i.category=="Food")temp[2]+=i.amount.toInt()
            else if(i.category=="Others")temp[3]+=i.amount.toInt()

        }
        return temp
    }


}