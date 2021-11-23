package com.example.userdetailsapplaunch.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.userdetailsapplaunch.R
import com.example.userdetailsapplaunch.viewmodel.UserViewModel


class UserFormFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_form, container, false)

        val saveButton = view.findViewById<Button>(R.id.buttonSave)
        saveButton.setOnClickListener(View.OnClickListener {
            suspend { userViewModel.insertData(
                context = this.requireContext(),
                username = "SANGEETHA",
                lastname = "RAMAKRISHNAN",
                emailid = "test@gmail.com"
            ) }
        })
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UserFormFragment().apply {
                arguments = Bundle().apply {
                    putString("ARG_PARAM1", param1)
                    putString("ARG_PARAM2", param2)
                }
            }
    }
}