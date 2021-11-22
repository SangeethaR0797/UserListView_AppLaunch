package com.example.userdetailsapplaunch.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserDetails(userTableModel: UserTableModel)

    @Query("SELECT * FROM UserDetails")
    fun getUserDetails() : LiveData<List<UserTableModel>>

    @Delete
    suspend fun deleteUserDetail(userTableModel: UserTableModel)

}