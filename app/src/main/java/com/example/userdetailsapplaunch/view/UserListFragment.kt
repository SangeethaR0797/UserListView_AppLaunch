package com.example.userdetailsapplaunch.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userdetailsapplaunch.R
import com.example.userdetailsapplaunch.model.UserModel
import com.example.userdetailsapplaunch.viewmodel.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A fragment representing a list of Items.
 */
class UserListFragment : Fragment() {

    private var columnCount = 1
    private lateinit var userViewModel:UserViewModel
    private lateinit var userList:List<UserModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }

        userViewModel=ViewModelProvider(this).get(UserViewModel::class.java)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.

        context?.let { userViewModel.getUserDetails(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_list_list, container, false)

        val fabAddUser=view.findViewById<FloatingActionButton>(R.id.fabAddUser)
        fabAddUser.setOnClickListener(View.OnClickListener {
            view.findNavController().navigate(R.id.action_userListFragment_to_userFormFragment)
        })
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }

                adapter = UserItemRecyclerViewAdapter(
                    listOf(
                        UserModel(
                            "Sangeetha",
                            "RK",
                            "sangeetha0797@gmail.com"
                        )
                    )
                )
            }
        }
        return view
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            UserListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}