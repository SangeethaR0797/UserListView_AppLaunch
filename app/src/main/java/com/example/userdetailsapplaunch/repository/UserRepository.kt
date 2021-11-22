package com.example.userdetailsapplaunch.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.userdetailsapplaunch.room.UserDataBase
import com.example.userdetailsapplaunch.room.UserTableModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class UserRepository()
{
    companion object {

        var userDatabase: UserDataBase? = null

        var userTableModel: LiveData<List<UserTableModel>>? = null

        fun initializeDB(context: Context) : UserDataBase {
            return UserDataBase.getDataseClient(context)
        }

        suspend fun insertUserDetails(context: Context, username: String, lastname: String,emailid:String) {

            userDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                val loginDetails = UserTableModel(username,lastname,emailid)
                userDatabase?.userDao()?.insertUserDetails(loginDetails)
            }

        }

        fun getUserDetails(context: Context) : LiveData<List<UserTableModel>>? {

            userDatabase = initializeDB(context)

            userTableModel = userDatabase?.userDao()?.getUserDetails()

            return userTableModel
        }

        suspend fun deleteUser(context: Context,userDetails:UserTableModel)
        {
            userDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                userDatabase?.userDao()?.deleteUserDetail(userDetails)
            }
        }

    }
}