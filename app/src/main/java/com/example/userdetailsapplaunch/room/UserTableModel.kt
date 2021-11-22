package com.example.userdetailsapplaunch.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserDetails")
data class UserTableModel(@ColumnInfo(name = "firstname") var FirstName:String,@ColumnInfo(name="lastname") var LastName:String,@PrimaryKey @ColumnInfo(name="emailid") var EmailId:String)
{

}
