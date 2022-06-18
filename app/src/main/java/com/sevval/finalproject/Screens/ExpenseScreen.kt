package com.sevval.finalproject.Screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sevval.finalproject.R
import com.sevval.finalproject.TransactionApplication
import com.sevval.finalproject.adapter.TransactionClickDeleteInterface
import com.sevval.finalproject.adapter.TransactionClickInterface
import com.sevval.finalproject.adapter.TransactionListAdapter
import com.sevval.finalproject.model.Transaction
import com.sevval.finalproject.viewmodel.TransactionViewModel
import com.sevval.finalproject.viewmodel.TransactionViewModelFactory

class ExpenseScreen : AppCompatActivity(),TransactionClickInterface,TransactionClickDeleteInterface {
    lateinit var adapter: TransactionListAdapter
    val expenseTransactions=ArrayList<Transaction>()
    lateinit var totalExpenseTxt:TextView
    lateinit var backButton: Button

    private val transactionViewModel: TransactionViewModel by viewModels {
        TransactionViewModelFactory((application as TransactionApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_screen)

        totalExpenseTxt=findViewById(R.id.totalExpenseID)
        backButton=findViewById(R.id.expenseBackButton)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        adapter = TransactionListAdapter(this,this,this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        transactionViewModel.allTransactions.observe(this){ transactions->

            transactions.let {
                var totalExpense:Int=0
                expenseTransactions.clear()

                totalExpenseTxt.text="0"
                for(item in it){
                    if (item.type.equals("Expense")){
                        expenseTransactions.add(item)
                        totalExpense=totalExpense+item.amount
                        totalExpenseTxt.text="-"+totalExpense.toString()

                    }
                }
                adapter.updateList(expenseTransactions)}
        }

        backButton.setOnClickListener {
            val intent= Intent(this@ExpenseScreen, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }

    }

    override fun onDeleteIconClick(transaction: Transaction) {
        Toast.makeText(this,"${transaction.title}", Toast.LENGTH_LONG).show()
        transactionViewModel.delete(transaction)
    }

    override fun onTransactionClick(transaction: Transaction) {
        Toast.makeText(this,"${transaction.title}", Toast.LENGTH_LONG).show()
    }
}