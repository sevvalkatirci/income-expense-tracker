package com.sevval.finalproject.Screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import com.google.android.material.textfield.TextInputLayout
import com.sevval.finalproject.R
import com.sevval.finalproject.TransactionApplication
import com.sevval.finalproject.model.Transaction
import com.sevval.finalproject.viewmodel.TransactionViewModel
import com.sevval.finalproject.viewmodel.TransactionViewModelFactory

class NewTransactionActivity : AppCompatActivity() {
    private lateinit var editTitleView: EditText
    private lateinit var editAmountView: EditText
    private lateinit var addUpdateBtn:Button
    private lateinit var textInputLayoutType:TextInputLayout
    private lateinit var autoCompleteTextType:AutoCompleteTextView
    public var total:Int=0
    var transactionID=-1
    lateinit var arrayList_type:ArrayList<String>
    lateinit var arrayAdapter_type: ArrayAdapter<String>

    private val transactionViewModel:TransactionViewModel by viewModels {
        TransactionViewModelFactory((application as TransactionApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_transaction)

        editTitleView=findViewById(R.id.edit_transaction)
        editAmountView=findViewById(R.id.edit_amount)
        addUpdateBtn=findViewById(R.id.button_save)
        textInputLayoutType=findViewById(R.id.type_TIL)
        autoCompleteTextType=findViewById(R.id.type_ACT)

        arrayList_type=ArrayList<String>()
        arrayList_type.add("Income")
        arrayList_type.add("Expense")

        arrayAdapter_type= ArrayAdapter(applicationContext,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arrayList_type)
        autoCompleteTextType.setAdapter(arrayAdapter_type)
        autoCompleteTextType.threshold=1

        (textInputLayoutType.getEditText() as AutoCompleteTextView).onItemClickListener=
            AdapterView.OnItemClickListener { adapterView, view, position, id ->
                val selectedValue:String=arrayAdapter_type.getItem(position).toString()
            }

        val actionType=intent.getStringExtra("actionType")
        if(actionType.equals("Edit")){
            val transactionTitle=intent.getStringExtra("transactionTitle")
            val transactionAmount=intent.getIntExtra("transactionAmount",-1)
            val transactionType=intent.getStringExtra("transactionType")
            transactionID=intent.getIntExtra("transactionID",-1)
            addUpdateBtn.setText("Update Transaction")
            editTitleView.setText(transactionTitle)
            editAmountView.setText(transactionAmount.toString())
            autoCompleteTextType.setText(transactionType)
        }else{
            addUpdateBtn.setText("Save Transaction")
        }
        addUpdateBtn.setOnClickListener {
            val transactionTitle=editTitleView.text.toString()
            val transactionAmount=editAmountView.text.toString()
            val transactionType=autoCompleteTextType.text.toString()

            if(actionType.equals("Edit")){
                if(transactionTitle.isNotEmpty()&&transactionAmount.isNotEmpty()&&transactionType.isNotEmpty()){
                    val updatedTransaction=
                        Transaction(transactionTitle,transactionAmount.toInt(),transactionType)
                    updatedTransaction.id=transactionID
                    transactionViewModel.update(updatedTransaction)
                    Toast.makeText(this,"Transaction Updated..",Toast.LENGTH_LONG).show()
                }
            }else{
                if(transactionTitle.isNotEmpty()&&transactionAmount.isNotEmpty()&&transactionType.isNotEmpty()){
                    transactionViewModel.insert(Transaction(transactionTitle,transactionAmount.toInt(),transactionType))
                    Toast.makeText(this,"Transaction Added..",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"Invalid input",Toast.LENGTH_LONG).show()
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}