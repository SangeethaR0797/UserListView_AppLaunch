package com.example.userdetailsapplaunch.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.userdetailsapplaunch.model.UserModel
import com.example.userdetailsapplaunch.repository.UserRepository
import com.example.userdetailsapplaunch.room.UserDataBase
import com.example.userdetailsapplaunch.room.UserTableModel
import com.example.userdetailsapplaunch.view.ui.login.LoginFormState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(Application()) {

    var repository: UserRepository=UserRepository()

    // on below line we are initializing
    // our dao, repository and all notes
    init {
        val dao = UserDataBase.getDataseClient(application).userDao()
        repository = UserRepository()
    }

    suspend fun insertData(context: Context, username: String, lastname: String, emailid: String) {
        UserRepository.insertUserDetails(username, lastname, emailid)
    }

    fun getUserDetails(context: Context) = viewModelScope.launch(Dispatchers.IO)
    {
        UserRepository.getUserDetails()
    }

    fun deleteNote(context: Context, userDetails: UserTableModel) =
        viewModelScope.launch(Dispatchers.IO) {
            UserRepository.deleteUser(context, userDetails)
        }


    private val addUserData:MutableLiveData<UserModel> = MutableLiveData<UserModel>()

    suspend fun insertUserData(userData:UserModel)
    {
        UserRepository.insertUserDetails(userData.firstName,userData.lastName,userData.emailId)
    }

}