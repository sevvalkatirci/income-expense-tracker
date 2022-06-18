package com.sevval.finalproject.Screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sevval.finalproject.RegisterScreen
import com.sevval.finalproject.R
import com.sevval.finalproject.TransactionApplication
import com.sevval.finalproject.adapter.TransactionClickDeleteInterface
import com.sevval.finalproject.adapter.TransactionClickInterface
import com.sevval.finalproject.adapter.TransactionListAdapter
import com.sevval.finalproject.model.Transaction
import com.sevval.finalproject.viewmodel.TransactionViewModel
import com.sevval.finalproject.viewmodel.TransactionViewModelFactory

class MainActivity : AppCompatActivity(),TransactionClickInterface,TransactionClickDeleteInterface {

    private lateinit var totalTextView:TextView
    private lateinit var incomeTextView:TextView
    private lateinit var expenseTextView:TextView
    lateinit var adapter:TransactionListAdapter

    private val transactionViewModel: TransactionViewModel by viewModels {
        TransactionViewModelFactory((application as TransactionApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        totalTextView=findViewById(R.id.allTransactionsTV)
        incomeTextView=findViewById(R.id.incomeTV)
        expenseTextView=findViewById(R.id.expenseTV)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        adapter = TransactionListAdapter(this,this,this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        transactionViewModel.allTransactions.observe(this){ transactions->
            transactions.let {
                var totalExpense=0
                var totalIncome=0
                var total=0
                adapter.updateList(it)
                    for(item in it){
                        if(item.type.equals("Expense")){
                           totalExpense=item.amount+totalExpense
                        }else{
                           totalIncome=item.amount+totalIncome
                        }
                    }
                    total=totalIncome-totalExpense
                    expenseTextView.text= "-$totalExpense"
                    incomeTextView.text="+$totalIncome"
                    totalTextView.text=total.toString()
            }
        }
        incomeTextView.setOnClickListener {
            val intent= Intent(this@MainActivity, IncomeScreen::class.java)
            startActivity(intent)
            this.finish()
        }
        expenseTextView.setOnClickListener {
            val intent= Intent(this@MainActivity, ExpenseScreen::class.java)
            startActivity(intent)
            this.finish()
        }

        val fab=findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener{
            val intent= Intent(this@MainActivity,NewTransactionActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    override fun onDeleteIconClick(transaction: Transaction) {
        transactionViewModel.delete(transaction)
        Toast.makeText(this,"${transaction.title} Deleted", Toast.LENGTH_LONG).show()
    }

    override fun onTransactionClick(transaction: Transaction) {
        val intent=Intent(this@MainActivity, NewTransactionActivity::class.java)
        intent.putExtra("actionType","Edit")
        intent.putExtra("transactionTitle",transaction.title)
        intent.putExtra("transactionAmount",transaction.amount)
        intent.putExtra("transactionType",transaction.type)
        intent.putExtra("transactionID",transaction.id)
        startActivity(intent)
        this.finish()
    }
}