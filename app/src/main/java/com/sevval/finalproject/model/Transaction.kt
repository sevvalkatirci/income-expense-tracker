package com.sevval.finalproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_table")
data class Transaction(
    @ColumnInfo(name = "title")
    val title:String,
    @ColumnInfo(name="amount")
    val amount:Int,
    @ColumnInfo(name = "type")
    val type:String,

) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id:Int=0
}