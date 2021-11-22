package com.example.userdetailsapplaunch.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserTableModel::class],version = 1,exportSchema = false)
abstract class UserDataBase() : RoomDatabase() {

    abstract fun userDao() : UserDAOAccess

    companion object {

        @Volatile
        private var INSTANCE: UserDataBase? = null

        fun getDataseClient(context: Context) : UserDataBase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, UserDataBase::class.java, "USER_DETAILS_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}