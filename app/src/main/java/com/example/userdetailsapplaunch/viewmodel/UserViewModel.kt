package com.example.userdetailsapplaunch.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.userdetailsapplaunch.repository.UserRepository
import com.example.userdetailsapplaunch.room.UserDataBase
import com.example.userdetailsapplaunch.room.UserTableModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(Application()) {

    var userDetailsList: LiveData<List<UserTableModel>> = TODO()
    val repository: UserRepository

    // on below line we are initializing
    // our dao, repository and all notes
    init {
        val dao = UserDataBase.getDataseClient(application).userDao()
        repository = UserRepository()
    }

    suspend fun insertData(context: Context, username: String, lastname: String, emailid: String) {
        UserRepository.insertUserDetails(context, username, lastname, emailid)
    }

    fun getLoginDetails(context: Context, username: String) = viewModelScope.launch(Dispatchers.IO)
    {
        UserRepository.getUserDetails(context)
    }

    fun deleteNote(context: Context, userDetails: UserTableModel) =
        viewModelScope.launch(Dispatchers.IO) {
            UserRepository.deleteUser(context, userDetails)
        }

}