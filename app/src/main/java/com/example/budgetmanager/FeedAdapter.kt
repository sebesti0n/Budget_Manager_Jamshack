package com.example.budgetmanager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FeedAdapter(private val feedlist: ArrayList<Feed>):RecyclerView.Adapter<FeedAdapter.feedviewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): feedviewholder {
        val itemview= LayoutInflater.from(parent.context).inflate(R.layout.item_list_card_veiw,parent,false)
        return feedviewholder(itemview)
    }

    override fun getItemCount(): Int {
        return feedlist.size
    }

    override fun onBindViewHolder(holder: feedviewholder, position: Int) {
        val currentitem =feedlist[position]

        holder.heading.text=currentitem.heading
        holder.content.text=currentitem.content

    }

    class feedviewholder(itemview: View):RecyclerView.ViewHolder(itemview) {
        val heading : TextView = itemview.findViewById(R.id.heading)
        val content: TextView =itemview.findViewById(R.id.content)
    }
}