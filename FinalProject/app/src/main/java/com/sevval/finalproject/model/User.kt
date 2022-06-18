package com.sevval.finalproject.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "UserInfo")
data class User(
    @ColumnInfo(name = "name")
    val userName:String,
    @ColumnInfo(name="password")
    val userPassword:String
) {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var userId:Int=0
}