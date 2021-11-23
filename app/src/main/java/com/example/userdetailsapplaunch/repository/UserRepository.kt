package com.example.userdetailsapplaunch.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import com.example.userdetailsapplaunch.room.UserDAOAccess
import com.example.userdetailsapplaunch.room.UserDataBase
import com.example.userdetailsapplaunch.room.UserTableModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

public class UserRepository()
{
    companion object {

        lateinit var userDatabase: UserDataBase

        var userTableModel: LiveData<List<UserTableModel>>? = null

        lateinit var userDAOAccess:UserDAOAccess

        fun initializeDB(context: Context) : UserDataBase {
            userDatabase= UserDataBase.getDataseClient(context)
            userDAOAccess= userDatabase.userDao()
            return userDatabase
        }

        suspend fun insertUserDetails(
            username: String,
            lastname: String,
            emailid: String
        ) {

            CoroutineScope(IO).launch {
                val loginDetails = UserTableModel(username, lastname, emailid)
                userDAOAccess.insertUserDetails(loginDetails)
            }

        }

        fun getUserDetails() : LiveData<List<UserTableModel>> {


            userTableModel = userDAOAccess.getUserDetails()

            return userTableModel as LiveData<List<UserTableModel>>
        }

        suspend fun deleteUser(context: Context, userDetails: UserTableModel)
        {

            CoroutineScope(IO).launch {
                userDAOAccess.deleteUserDetail(userDetails)
            }
        }

    }
}