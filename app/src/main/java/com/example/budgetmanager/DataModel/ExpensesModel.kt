package com.example.budgetmanager.DataModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExpensesModel(
    var category: String,
    val shortdesc: String,
    val amount:String,
    val date:String

){
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null
}
