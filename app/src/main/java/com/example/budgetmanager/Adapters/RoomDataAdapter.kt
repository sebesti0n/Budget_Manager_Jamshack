package com.example.budgetmanager.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetmanager.DataModel.ExpensesModel
import com.example.budgetmanager.R

class RoomDataAdapter(var expensesList:ArrayList<ExpensesModel>):
    RecyclerView.Adapter<RoomDataAdapter.RoomDataViewHolder>() {
    class RoomDataViewHolder(val v: View):RecyclerView.ViewHolder(v) {
        val tvCategory:TextView=v.findViewById(R.id.tv_category)
        val tvShortDesc:TextView=v.findViewById(R.id.tv_shortDesc)
        val tvDate:TextView=v.findViewById(R.id.tv_date)
        val tvAmount:TextView=v.findViewById(R.id.tv_amount)

    }
    fun setData(list:ArrayList<ExpensesModel>){
        this.expensesList=list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomDataViewHolder {
        return RoomDataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_expense, parent, false))
    }

    override fun getItemCount(): Int {
   return expensesList.size
    }

    override fun onBindViewHolder(holder: RoomDataViewHolder, position: Int) {
        val data = expensesList[position]
       holder.tvAmount.text= data.amount
        holder.tvCategory.text= data.category
        holder.tvDate.text=data.date
        holder.tvShortDesc.text=data.shortdesc
    }

}