package com.sevval.finalproject.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.ColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.blue
import androidx.recyclerview.widget.RecyclerView
import com.sevval.finalproject.R
import com.sevval.finalproject.model.Transaction

class TransactionListAdapter(
    val context: Context,
    val transactionClickDeleteInterface: TransactionClickDeleteInterface,
    val transactionClickInterface: TransactionClickInterface
):RecyclerView.Adapter<TransactionListAdapter.ViewHolder>() {

    val allTransactions=ArrayList<Transaction>()

    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val titleTV=itemView.findViewById<TextView>(R.id.titleTV)
        val amountTV=itemView.findViewById<TextView>(R.id.amountTV)
        var deleteIV=itemView.findViewById<ImageView>(R.id.deleteIV)
        var typeImage=itemView.findViewById<ImageView>(R.id.typeIV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTV.setText(allTransactions.get(position).title)
        holder.amountTV.setText(allTransactions.get(position).amount.toString())

        if(allTransactions.get(position).type.equals("Income")){
            holder.typeImage.setImageResource(R.drawable.ic_baseline_arrow_downward_24)
            holder.typeImage.setColorFilter(Color.GREEN);
        }else{
            holder.typeImage.setImageResource(R.drawable.ic_baseline_arrow_upward_24)
            holder.typeImage.setColorFilter(Color.RED);
        }
        holder.deleteIV.setOnClickListener{
            transactionClickDeleteInterface.onDeleteIconClick(allTransactions.get(position))
        }
        holder.itemView.setOnClickListener{
            transactionClickInterface.onTransactionClick(allTransactions.get(position))
        }
    }

    override fun getItemCount(): Int {
        return allTransactions.size
    }

    fun updateList(newList:List<Transaction>){
        allTransactions.clear()
        allTransactions.addAll(newList)
        notifyDataSetChanged()
    }
}
interface TransactionClickDeleteInterface{
    fun onDeleteIconClick(transaction: Transaction)
}
interface TransactionClickInterface{
    fun onTransactionClick(transaction: Transaction)
}